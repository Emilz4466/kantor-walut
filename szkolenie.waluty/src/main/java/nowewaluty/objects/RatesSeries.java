package nowewaluty.objects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import nowewaluty.Currency;

public class RatesSeries {
	private String table;
	private Currency code;
	private List<Rate> rates;

	public RatesSeries() {
	}

	public RatesSeries(String table, Currency code, List<Rate> rates) {
		super();
		this.table = table;
		this.code = code;
		this.rates = rates;
	}

	public String getTable() {
		return table;
	}

	public Currency getCode() {
		return code;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setCode(String code) {
		this.code = Currency.valueOf(code);
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public static class Rate {
		private LocalDate effectiveDate;
		private BigDecimal mid;

		public Rate() {
		}

		public Rate(LocalDate effectiveDate, BigDecimal mid) {
			super();
			this.effectiveDate = effectiveDate;
			this.mid = mid;
		}

		public LocalDate getEffectiveDate() {
			return effectiveDate;
		}

		public BigDecimal getMid() {
			return mid;
		}

		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = LocalDate.parse(effectiveDate);
		}

		public void setMid(String mid) {
			this.mid = new BigDecimal(mid);
		}

	}
}
