package interfaces;

public interface Shippable {

	public default double getWeight() {
		return 10.0d;
	}

	public default double getHeight() {
		return 10.0d;
	}

	public default double getWidth() {
		return 10.0d;
	}

	public default double getLength() {
		return 10.0d;
	}
}
