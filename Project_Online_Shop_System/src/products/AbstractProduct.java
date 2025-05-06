package products;

import interfaces.Product;
import interfaces.UnitsSold;

public abstract class AbstractProduct implements Product, Comparable<AbstractProduct>, UnitsSold {

	private Price price;
	private String name;

	public AbstractProduct(String name, Price price) {
		this.name = name;
		this.price = price;
	}

	// Aufgabe 1.e
	// Vergleichungsmethode
	// java.lang.Comparable
	@Override
	public int compareTo(AbstractProduct p) {
		return (int) ((this.price.getGrossPrice() - p.getPrice().getGrossPrice()) * 10);
	}

	public Price getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

}
