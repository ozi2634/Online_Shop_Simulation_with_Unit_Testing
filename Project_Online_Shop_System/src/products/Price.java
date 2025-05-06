package products;

public class Price {

	private double netPrice;
	private Tax taxClass;

	public Price(double netPrice, Tax taxClass) {
		this.netPrice = netPrice;
		this.taxClass = taxClass;
	}

	public double getNetPrice() {
		return netPrice;
	}

	public double getGrossPrice() {
		return taxClass.getPriceWithTax(netPrice);
	}
}
