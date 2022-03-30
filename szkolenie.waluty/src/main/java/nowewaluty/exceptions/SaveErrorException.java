package nowewaluty.exceptions;

@SuppressWarnings("serial")
public class SaveErrorException extends RuntimeException {

	public SaveErrorException(String message, Exception e) {
		super(message, e);
	}

	public SaveErrorException(String message) {
		super(message);
	}
}