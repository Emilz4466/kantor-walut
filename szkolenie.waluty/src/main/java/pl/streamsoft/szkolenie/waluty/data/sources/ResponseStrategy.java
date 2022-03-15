package pl.streamsoft.szkolenie.waluty.data.sources;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;

public interface ResponseStrategy {
	String getResponse() throws NoDataException;

	void setFormat(String format);
}
