package nowewaluty.sources.session.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import nowewaluty.Currency;
import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.objects.RatesSeries;
import nowewaluty.objects.RatesSeries.Rate;
import nowewaluty.objects.db.RateValue;
import nowewaluty.parsers.parser.JsonParser;
import nowewaluty.sources.ApiSource;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.strategies.Dao;

public class RateValueDao implements Dao<RateValue> {

	SessionFactory sessionFactory;
	HibernateFactory factory;

	public RateValueDao(HibernateFactory factory) throws DaoSessionException {
		this.factory = factory;
		try {
			sessionFactory = factory.factory();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}
	}

	@Override
	public RateValue get(long id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM RateValue WHERE rate_id = :id");
		query.setParameter("id", id);
		RateValue rateValue = (RateValue) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return rateValue;

	}

	@Override
	public void save(RateValue t) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void update(long id, RateValue t) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("UPDATE RateValue SET value =:value, date=:date, currency_id =:currency_id WHERE id =:id");
		query.setParameter("value", t.getValue());
		query.setParameter("date", t.getDate());
		query.setParameter("currency_id", t.getCodes().getId());
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();

	}

	public RateValue get(Currency currency, LocalDate date) throws DaoSessionException {

		CurrencyCodeDao currencyCodeDao = new CurrencyCodeDao(factory);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM RateValue WHERE date =:date AND currency_id=:code_id");
		query.setParameter("date", date);
		query.setParameter("code_id", currencyCodeDao.get(currency).getId());
		RateValue rateValue = (RateValue) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return rateValue;

	}

	public Object[] getHighestDiffrenceCourse(LocalDate startDate, LocalDate endDate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedNativeQuery("Rate_HighestDiffrenceInPeriod");
		query.setParameter("date1", startDate);
		query.setParameter("date2", endDate);

		Object[] result = (Object[]) query.uniqueResult();
		session.getTransaction().commit();
		session.close();

		return result;
	}

	public RateValue[] getMinAndMaxRateInPeriod(LocalDate startDate, LocalDate endDate, Currency code)
			throws DaoSessionException {

		RateValue[] rate = new RateValue[2];

		CurrencyCodeDao currencyDao = new CurrencyCodeDao(factory);
		long codeId = currencyDao.get(code).getId();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query queryMin = session.getNamedQuery("Rate_MinRateInPeriod");
		queryMin.setParameter("date1", startDate);
		queryMin.setParameter("date2", endDate);
		queryMin.setParameter("currency", codeId);
		rate[0] = (RateValue) queryMin.uniqueResult();

		Query queryMax = session.getNamedQuery("Rate_MaxRateInPeriod");
		queryMax.setParameter("date1", startDate);
		queryMax.setParameter("date2", endDate);
		queryMax.setParameter("currency", codeId);
		rate[1] = (RateValue) queryMax.uniqueResult();

		return rate;
	}

	public List<RateValue>[] getFiveBestAndWorstRates(Currency code) throws DaoSessionException {
		List<RateValue>[] rates = new ArrayList[2];

		CurrencyCodeDao currencyDao = new CurrencyCodeDao(factory);
		long codeId = currencyDao.get(code).getId();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<RateValue> bestRates = new ArrayList<>();
		Query queryBest = session.getNamedQuery("Rate_BestFiveRates");
		queryBest.setParameter("currency", codeId);
		queryBest.setMaxResults(5);
		bestRates = queryBest.getResultList();
		rates[0] = bestRates;

		List<RateValue> worstRates = new ArrayList<>();
		Query queryWorst = session.getNamedQuery("Rate_WorstFiveRates");
		queryWorst.setParameter("currency", codeId);
		queryWorst.setMaxResults(5);
		worstRates = queryWorst.getResultList();
		rates[1] = worstRates;

		return rates;
	}

	public void saveRatesFromApi() throws Exception {
		ApiSource api = new ApiSource();
		JsonParser parser = new JsonParser();

		CurrencyCodeDao currencyDao = new CurrencyCodeDao(factory);

		LocalDate date1 = LocalDate.of(2002, 02, 02);
		LocalDate date2 = LocalDate.of(2003, 02, 02);
		Set<RateValue> ratesData = new HashSet<>();
		System.out.println("Pobrano i przekonwertowano: ");
		for (Currency code : Currency.values()) {
			for (int i = 0; i < 20; i++) {
				date1 = date1.plusYears(i);
				date2 = date2.plusYears(i);
				RatesSeries series = api.getRates(parser, code, date1, date2);
				if (series != null) {
					for (Rate rate : series.getRates()) {
						ratesData.add(new RateValue(rate.getMid(), rate.getEffectiveDate(), currencyDao.get(code)));
					}
				}
				date1 = LocalDate.of(2002, 02, 02);
				date2 = LocalDate.of(2003, 02, 02);
			}
			System.out.println(ratesData.size());
		}
		int counter = 0;
		System.out.println("Zapisano w bazie: ");
		for (RateValue rate : ratesData) {
			this.save(rate);
			counter++;
			if (counter % 1000 == 0) {
				System.out.println(counter);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		HibernateFactory factory = new HibernateFactory();
		RateValueDao dao = new RateValueDao(factory);

	}

}
