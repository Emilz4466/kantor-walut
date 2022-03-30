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
@Table(name = "countries")
public class Country implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "country_id")
	private Long id;

	@Column(name = "country_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "currency_id")
	private CurrencyCode code;

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
