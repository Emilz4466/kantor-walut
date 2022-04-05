package nowewaluty.sources;

import java.math.MathContext;
import java.time.LocalDate;

import nowewaluty.Currency;
import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.exceptions.SaveErrorException;
import nowewaluty.objects.RateData;
import nowewaluty.objects.db.CurrencyCode;
import nowewaluty.objects.db.RateValue;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.sources.session.dao.CurrencyCodeDao;
import nowewaluty.sources.session.dao.RateValueDao;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class DatabaseSource implements SourceStrategy {

	private Currency currency;
	private LocalDate date;

	private HibernateFactory factory = new HibernateFactory();

	public DatabaseSource() {
	}

	public DatabaseSource(Currency currency, LocalDate date) {

		this.currency = currency;
		this.date = date;

	}

	public RateValue response() throws Exception {
		RateValueDao rateValueDao = new RateValueDao(factory);

		RateValue result = rateValueDao.get(currency, date);

		return result;
	}

	@Override
	public void saveRate(RateData rate) {

		try {
			CurrencyCodeDao currencyCodeDao = new CurrencyCodeDao(factory);
			RateValueDao rateValueDao = new RateValueDao(factory);
			CurrencyCode currencyCode = currencyCodeDao.get(rate.getCurrency());

			if (currencyCode == null) {
				currencyCodeDao.save(new CurrencyCode(rate.getCurrency().code.toUpperCase()));
			}
			currencyCode = currencyCodeDao.get(rate.getCurrency());

			RateValue valueInDb = rateValueDao.get(rate.getCurrency(), rate.getDate());
			if (valueInDb == null) {
				rateValueDao.save(new RateValue(rate.getRate(), rate.getDate(), currencyCode));
			} else if (valueInDb.getValue().round(new MathContext(5))
					.equals(rate.getRate().round(new MathContext(5)))) {
				rateValueDao.update(valueInDb.getId(), new RateValue(rate.getRate(), rate.getDate(), currencyCode));
			}

		} catch (DaoSessionException e) {
			throw new SaveErrorException("Błąd przy zapisie do bazy danych", e);
		}

	}

	@Override
	public RateData getRate(ParserStrategy parser) {
		RateValue rate = null;

		try {
			rate = response();

			if (rate == null) {
				return null;
			} else {
				RateData rateData = new RateData(date, currency, rate.getValue());
				return rateData;
			}

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;

	}

}
