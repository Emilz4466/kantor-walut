package pl.streamsoft.szkolenie.waluty.data.sources.cache;

import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;

public class Key {
	private Currency key1;
	private LocalDate key2;

	public Key(Currency key1, LocalDate key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Key)) {
			return false;
		}

		Key that = (Key) object;

		return key1 == that.key1 && key2 == that.key2;
	}

	@Override
	public int hashCode() {
		int result = key1.hashCode();
		result = 31 * result + key2.hashCode();
		return result;

	}

}
