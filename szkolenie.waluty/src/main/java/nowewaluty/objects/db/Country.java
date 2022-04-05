package nowewaluty.objects.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = "Country_CountryWithMoreThanOneCurrency", query = "FROM Country ctr JOIN currencies_countries cc ON cc.country_id = ctr.country_id GROUP BY ctr.country_id HAVING size(cc.currency_id)>= 2")
@SuppressWarnings("serial")
@Entity
@Table(name = "countries")
public class Country implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "country_id")
	private Long id;

	@Column(name = "country_name")
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\n		[id=" + id + "]\n		[name=" + name + "]";
	}

}
