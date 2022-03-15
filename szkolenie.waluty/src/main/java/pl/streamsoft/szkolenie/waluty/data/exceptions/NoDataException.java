package pl.streamsoft.szkolenie.waluty.data.exceptions;

import java.io.IOException;

public class NoDataException extends Exception {

	public NoDataException(String message, IOException e) {
		super(message, e);
	}
}
