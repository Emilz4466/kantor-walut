package pl.streamsoft.szkolenie.waluty.data;

import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

public class ConnectionMethod {
	private SourceStrategy source;
	private ParserStrategy parser;

	public ConnectionMethod(SourceStrategy source, ParserStrategy parser) {
		super();
		this.source = source;
		this.parser = parser;
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
