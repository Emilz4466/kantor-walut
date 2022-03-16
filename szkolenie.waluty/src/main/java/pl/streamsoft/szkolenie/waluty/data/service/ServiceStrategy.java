package pl.streamsoft.szkolenie.waluty.data.service;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;

public interface ServiceStrategy {

	String getResponse() throws NoDataException;

}
