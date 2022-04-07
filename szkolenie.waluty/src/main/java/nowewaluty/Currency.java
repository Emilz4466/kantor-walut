package nowewaluty;

public enum Currency {
	ATS("ats"), AUD("aud"), BEF("bef"), BGN("bgn"), BRL("brl"), CAD("cad"), CHF("chf"), CLP("clp"), CNY("cny"),
	CYP("cyp"), CZK("czk"), DKK("dkk"), EEK("eek"), ESP("esp"), EUR("eur"), FIM("fim"), FRF("frf"), GBP("gbp"),
	GRD("grd"), HKD("hkd"), HRK("hrk"), HUF("huf"), IDR("idr"), IEP("iep"), ILS("ils"), INR("inr"), ISK("isk"),
	ITL("itl"), JPY("jpy"), KRW("krw"), LTL("ltl"), LUF("luf"), LVL("lvl"), MTL("mtl"), MXN("mxn"), MYR("myr"),
	NLG("nlg"), NOK("nok"), NZD("nzd"), PHP("php"), PTE("pte"), RON("ron"), RUB("rub"), SEK("sek"), SGD("sgd"),
	SIT("sit"), SKK("skk"), THB("thb"), TRY("try"), UAH("uah"), USD("usd"), XDR("xdr"), ZAR("zar");

	public final String code;

	private Currency(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
