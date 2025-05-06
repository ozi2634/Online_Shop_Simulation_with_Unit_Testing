package interfaces;

import products.Price;

public interface Product {

	public Price getPrice();

	public String getName();

	public boolean sell();

	public boolean returnProduct();
}
