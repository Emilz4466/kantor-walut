package nowewaluty.strategies;

import nowewaluty.objects.RatesSeries;

public interface ParserStrategy {
	RatesSeries getRatesSeries(String response);

	String getFormat();
}
