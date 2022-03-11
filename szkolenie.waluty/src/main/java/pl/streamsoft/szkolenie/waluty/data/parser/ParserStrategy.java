package pl.streamsoft.szkolenie.waluty.data.parser;

import java.math.BigDecimal;

import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;

public interface ParserStrategy {
	BigDecimal getRate(ResponseStrategy response);
}
