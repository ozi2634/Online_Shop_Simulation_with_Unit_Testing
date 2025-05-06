package shop;

import core.Container;
import core.DoubleLinkedList;
import interfaces.Downloadable;
import interfaces.Shippable;

public class Customer {

	private String name;
	private String adress;
	private String emailAdress;
	private DoubleLinkedList ownedProducts = new DoubleLinkedList();
	private double money;

	private static int customerID = 0;

	public Customer(String name, String adress, String emailAdress) {
		this.adress = adress;
		this.name = name;
		this.emailAdress = emailAdress;

	}

	public Customer() {
		this.adress = "" + customerID;
		this.name = "" + customerID;
		this.emailAdress = "" + customerID;
		customerID++;
	}

	public void recieveProduct(Shippable p) {
		ownedProducts.add(p);
	}

	public void downloadProduct(Downloadable p) {
		ownedProducts.add(p);
	}

	public double getAvailableMoney() {
		return money;
	}

	public void changeMoney(double amount) {
		money += amount;
	}

	public Container getProducts() {
		return ownedProducts;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public String getAdress() {
		return adress;
	}

	public String getName() {
		return name;
	}

}