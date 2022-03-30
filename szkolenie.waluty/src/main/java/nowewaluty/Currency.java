package nowewaluty;

public enum Currency {
	AUD("aud"), ATS("ats"), BEF("bef"), CZK("czk"), DKK("dkk"), EEK("eek"), FIM("fim"), FRF("frf"), GRD("grd"),
	ESP("esp"), NLG("nlg"), IEP("iep"), JPY("jpy"), CAD("cad"), LUF("luf"), NOK("nok"), PTE("pte"), EUR("eur"),
	USD("usd"), CHF("chf"), SEK("sek"), HUF("huf"), GBP("gbp"), ITL("itl"), XDR("xdr");

	public final String code;

	private Currency(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
