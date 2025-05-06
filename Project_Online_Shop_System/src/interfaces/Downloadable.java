package interfaces;

public interface Downloadable {

	public default double getDownloadSize() {
		return 10.0d;
	}
}
