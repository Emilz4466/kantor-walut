package nowewaluty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nowewaluty.exceptions.NoDataException;
import nowewaluty.objects.RateData;

public class Exchanger {

	private List<ResponseMethod> responses;
	private List<RateData> rates = new ArrayList<>();

	private BigDecimal value;
	private List<BigDecimal> result = new ArrayList<>();

	public Exchanger() {

	}

	public void dateValidator() {
		for (int i = 0; i <= 20; i++) {
			for (ResponseMethod method : responses) {
				LocalDate dateInSrc = method.getSource().getDate();
				if (dateInSrc != null) {
					if (method.getRateData() == null) {
						method.getSource().setDate(dateInSrc.minusDays(1));
						dateInSrc = dateInSrc.minusDays(1);
					} else {
						break;
					}
					if (i == 5) {
						throw new NoDataException("Brak wskazanej daty dla zródła " + method.getSource());
					}
				}

			}
		}
	}

	private void saveRates() {
		List<RateData> ratesToSave = new ArrayList<>();
		for (ResponseMethod method : responses) {
			RateData rateData = method.getRateData();
			ratesToSave.add(rateData);
		}
		for (ResponseMethod method : responses) {
			for (RateData rate : ratesToSave) {
				if (rate != null && rate.getDate() != null && rate.getCurrency() != null && rate.getRate() != null) {
					method.getSource().saveRate(rate);
				}

			}
		}
	}

	private void setRates() {
		saveRates();
		dateValidator();
		for (ResponseMethod method : responses) {
			RateData rateData = method.getRateData();
			rates.add(rateData);
		}
	}

	private void setResults() {
		BigDecimal divider = new BigDecimal(1);

		for (RateData rate : rates) {
			if (rate == null) {
				throw new NoDataException("Brak wartości dla wskazanej daty lub kodu");
			}
			BigDecimal currentRate = rate.getRate();
			BigDecimal invertedRate = divider.divide(currentRate, MathContext.DECIMAL128);
			result.add(value.multiply(invertedRate));
		}
	}

	public List<BigDecimal> exchange(BigDecimal value, List<ResponseMethod> responseMethods) {
		this.value = value;
		this.responses = responseMethods;

		setRates();
		setResults();

		return result;

	}

	public List<ResponseMethod> getResponses() {
		return responses;
	}

}
