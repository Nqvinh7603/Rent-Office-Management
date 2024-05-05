package site.rentofficevn.constant.customexception;

public class FieldRequireException extends RuntimeException {
	public FieldRequireException(String errorMessage) {
		super(errorMessage);
	}
}
