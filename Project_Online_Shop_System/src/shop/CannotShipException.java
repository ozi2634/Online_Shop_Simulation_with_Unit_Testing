package shop;

public class CannotShipException extends Exception {

	public CannotShipException() {
		super();
	}

	public CannotShipException(String message) {
		super(message);
	}
}
