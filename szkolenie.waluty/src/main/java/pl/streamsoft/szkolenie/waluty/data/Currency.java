package pl.streamsoft.szkolenie.waluty.data;

public enum Currency {
	EUR("eur"),
	USD("usd"),
	RUB("rub");

	public final String code;
	
	private Currency(String code) {
		this.code = code;
	}
	
	
}
