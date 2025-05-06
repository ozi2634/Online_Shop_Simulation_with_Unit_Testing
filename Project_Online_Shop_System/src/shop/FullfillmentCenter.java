package shop;

import org.hamcrest.core.StringEndsWith;

import core.Container;
import core.DoubleLinkedList;
import interfaces.Downloadable;
import interfaces.Product;
import interfaces.Shippable;
import products.AbstractProduct;
import products.Book;
import products.Computer;
import products.Price;
import products.Tax;

// Aufgabe 2.e

public class FullfillmentCenter {
	
	public void sendProducts(ShoppingCard cart) throws CannotShipException {
		int cartSize = cart.getProducts().size();
		Container products = cart.getProducts();  // eine Liste der Produkte
		Customer customer = cart.getCustomer();
		
		for (int i = 0; i < cartSize; i++) {
			
			Object product = products.get(i);
			
			//  Wenn ein Produkt nicht auslieferbar ist.
			if (product instanceof Shippable) {
				customer.recieveProduct((Shippable)product);
			}else if (product instanceof Downloadable) {
				customer.downloadProduct((Downloadable)product);
			}else {
				throw new CannotShipException();
			}
			
			((AbstractProduct)product).sell();
			
		}
		
		for (int i = 0; i < cartSize; i++) {
			products.remove(products.get(0));
		}		
	}
}