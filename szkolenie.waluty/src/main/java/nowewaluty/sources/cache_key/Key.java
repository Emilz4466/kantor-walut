package nowewaluty.sources.cache_key;

import java.time.LocalDate;
import java.util.Objects;

import nowewaluty.Currency;

public class Key {
	private Currency key1;
	private LocalDate key2;

	public Key(Currency key1, LocalDate key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key1, key2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		return key1 == other.key1 && Objects.equals(key2, other.key2);
	}
}
