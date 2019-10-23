package exception;

public class TimeOverlapException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3849903852197855816L;

	public TimeOverlapException() {
	}

	public TimeOverlapException(String message) {
		super(message);
	}

	public TimeOverlapException(Throwable cause) {
		super(cause);
	}

	public TimeOverlapException(String message, Throwable cause) {
		super(message, cause);
	}
}
