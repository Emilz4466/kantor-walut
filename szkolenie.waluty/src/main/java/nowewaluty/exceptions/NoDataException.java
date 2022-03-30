package nowewaluty.exceptions;

import java.io.IOException;

@SuppressWarnings("serial")
public class NoDataException extends RuntimeException {

	public NoDataException(String message, IOException e) {
		super(message, e);
	}

	public NoDataException(String message) {
		super(message);
	}
}
