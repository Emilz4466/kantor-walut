package pl.streamsoft.szkolenie.waluty.data.parser;

import java.math.BigDecimal;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;

public interface ParserStrategy {
	String getFormat();

	BigDecimal getRate(String response) throws NoDataException;
}
