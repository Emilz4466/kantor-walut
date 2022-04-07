package nowewaluty.objects.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO 
@NamedQuery(name = "Country_CountryWithMoreThanOneCurrency", query = "from Country ctr WHERE size(ctr.currencies)>=2")
@SuppressWarnings("serial")
@Entity
@Table(name = "countries")
public class Country implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_id")
	private Long id;

	@Column(name = "country_name")
	private String name;

	@ManyToMany(mappedBy = "countries")
	private Set<CurrencyCode> currencies = new HashSet<>();

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
