package products;

public enum Tax {
	FULL(0.19), REDUCED(0.07);

	public double tax;

	Tax(double i) {
		this.tax = i;
	}

	public double getPriceWithTax(double amount) {
		return amount + amount * this.tax;
	}
}
