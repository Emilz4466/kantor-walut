package pl.streamsoft.szkolenie.waluty.exchanger;

import java.math.BigDecimal;
import java.math.MathContext;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;

public class CurrencyExchanger {

	private ParserStrategy parser;
	private ResponseStrategy response;

	public CurrencyExchanger() {
	}

	private BigDecimal getCalculateRate() throws NoDataException {

		response.setFormat(parser.getFormat());

		System.out.println(response);
		BigDecimal rate = parser.getRate(response.getResponse());
		BigDecimal divider = new BigDecimal(1);

		BigDecimal invertedRate = divider.divide(rate, MathContext.DECIMAL128);

		return invertedRate;
	}

	public BigDecimal exchange(ResponseStrategy response, BigDecimal value, ParserStrategy parser) {

		this.parser = parser;
		this.response = response;

		try {
			return value.multiply(getCalculateRate());
		} catch (NoDataException e) {
			e.printStackTrace();
			return null;
		}

	}
}
