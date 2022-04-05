package nowewaluty.objects.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "currencies_countries")
public class CurrencyToCountry implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "currencies_countries_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "currencies_currency_id")
	private CurrencyCode code;

	@ManyToOne
	@JoinColumn(name = "countries_country_id")
	private Country country;
}
