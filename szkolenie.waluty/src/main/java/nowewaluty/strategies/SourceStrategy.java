package nowewaluty.strategies;

import java.time.LocalDate;

import nowewaluty.objects.RateData;

public interface SourceStrategy {

	RateData getRate(ParserStrategy parser);

	default void saveRate(RateData rateData) {
	}

	LocalDate getDate();

	void setDate(LocalDate date);
}
