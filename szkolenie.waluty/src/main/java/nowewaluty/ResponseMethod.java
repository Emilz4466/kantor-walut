package nowewaluty;

import nowewaluty.objects.RateData;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class ResponseMethod {
	private SourceStrategy source;
	private ParserStrategy parser;

	public ResponseMethod(SourceStrategy source, ParserStrategy parser) {
		super();
		this.source = source;
		this.parser = parser;
	}

	public ResponseMethod(SourceStrategy source) {
		this.source = source;
	}

	public RateData getRateData() {

		RateData rateData = source.getRate(parser);

		if (rateData == null) {
			return null;
		} else {
			return rateData;
		}
	}

	public SourceStrategy getSource() {
		return source;
	}

	public ParserStrategy getParser() {
		return parser;
	}

	public void setSource(SourceStrategy source) {
		this.source = source;
	}

	public void setParser(ParserStrategy parser) {
		this.parser = parser;
	}
}
