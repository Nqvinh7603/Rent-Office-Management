package site.rentofficevn.customexception;

public class FieldRequireException extends RuntimeException {
	public FieldRequireException(String errorMessage) {
		super(errorMessage);
	}
}
