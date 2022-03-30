package nowewaluty.objects.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
public class CurrencyCode {

	@Id
	@GeneratedValue
	@Column(name = "currency_id")
	private Long id;

	@Column(name = "currency_code")
	private String code;

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
