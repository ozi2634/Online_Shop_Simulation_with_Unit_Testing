package shop;

import core.Container;
import core.DoubleLinkedList;
import interfaces.Product;

public class ShoppingCard {

	// products: Eine doppelt verkettete Liste (DoubleLinkedList), die die Produkte
	// im Einkaufswagen speichert.
	// customer: Ein Objekt der Klasse Customer, das den Kunden repräsentiert, der
	// diesen Einkaufswagen benutzt.
	private DoubleLinkedList products = new DoubleLinkedList();
	private Customer customer;

	public ShoppingCard(Customer c) {
		customer = c;
	}

	// Ein Produktobjekt, das dem Einkaufswagen hinzugefügt werden soll.
	public boolean addProduct(Product p) {
		return products.add(p);
	}

	// Entfernt ein Produkt aus der products-Liste.
	public boolean removeProduct(Product p) {
		return products.remove(p);
	}

	// Ermöglicht den Zugriff auf den Kunden.
	public Customer getCustomer() {
		return customer;
	}

	public double getGrossPrice() {
		int priceSum = 0;
		for (int i = 0; i < products.size(); i++) {
			double temp = ((Product) products.get(i)).getPrice().getGrossPrice();
			priceSum += (int) (temp * 100);
		}
		return priceSum / 100d;
	}

	// Ermöglicht den Zugriff auf die im Einkaufswagen enthaltenen Produkte.
	public Container getProducts() {
		return products;
	}

}