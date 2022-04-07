package nowewaluty.objects.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = "CurrencyCode_AllCodes", query = "FROM CurrencyCode ORDER BY currency_code asc")

@Entity
@Table(name = "currencies")
public class CurrencyCode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_id")
	private Long id;

	@Column(name = "currency_code")
	private String code;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "currencies_countries", joinColumns = {
			@JoinColumn(name = "currency_id") }, inverseJoinColumns = { @JoinColumn(name = "country_id") })
	Set<Country> countries = new HashSet<>();

	public CurrencyCode() {
	}

	public CurrencyCode(String code) {
		super();
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "\n	[id=" + id + "]\n	[code=" + code + "]\n";

	}

}
