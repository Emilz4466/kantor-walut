package nowewaluty.objects.db;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
		@NamedQuery(name = "Rate_MinRateInPeriod", query = "FROM RateValue r WHERE (r.date BETWEEN :date1 AND :date2 ) AND currency_id = :currency AND r.value =(SELECT MIN(value) FROM RateValue WHERE (date BETWEEN :date1 AND :date2 ) AND currency_id = :currency)"),
		@NamedQuery(name = "Rate_MaxRateInPeriod", query = "FROM RateValue r WHERE (r.date BETWEEN :date1 AND :date2 ) AND currency_id = :currency AND r.value =(SELECT MAX(value) FROM RateValue WHERE (date BETWEEN :date1 AND :date2 ) AND currency_id = :currency)"),
		@NamedQuery(name = "Rate_BestFiveRates", query = "FROM RateValue r WHERE currency_id = :currency ORDER BY r.value asc"),
		@NamedQuery(name = "Rate_WorstFiveRates", query = "FROM RateValue r WHERE currency_id = :currency ORDER BY r.value desc") })

@NamedNativeQuery(name = "Rate_HighestDiffrenceInPeriod", query = "SELECT (max(r.value)- min(r.value)) AS result , c.currency_code FROM rates r JOIN currencies c ON c.currency_id = r.currency_id WHERE r.date BETWEEN :date1 AND :date2 GROUP BY c.currency_code ORDER BY result desc limit 1")

@Entity
@Table(name = "rates")
public class RateValue {

	@Id
	@GeneratedValue
	@Column(name = "rate_id")
	private Long id;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "date")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "currency_id", unique = true)
	private CurrencyCode code;

	public RateValue() {

	}

	public RateValue(BigDecimal value, LocalDate date, CurrencyCode code) {
		super();
		this.value = value;
		this.date = date;
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public LocalDate getDate() {
		return date;
	}

	public CurrencyCode getCodes() {
		return code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setCodes(CurrencyCode code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Rate \n" + "[id=" + id + "]\n[value=" + value + "]\n[date=" + date + "]\n[code=" + code.toString()
				+ "]";

	}

}
