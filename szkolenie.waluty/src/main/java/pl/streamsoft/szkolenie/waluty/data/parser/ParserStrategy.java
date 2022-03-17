package pl.streamsoft.szkolenie.waluty.data.parser;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.objects.RatesSeries;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

public interface ParserStrategy {
	String getFormat();

	RatesSeries getCurrencyObject(SourceStrategy source) throws NoDataException;
}
