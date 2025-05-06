package shop;

import core.DoubleLinkedList;

public class Shop {

	private static double availableMoney = 0;
	private static FullfillmentCenter ffCenter = new FullfillmentCenter();

	public static ShoppingCard newShoppingCard(Customer c) {
		return new ShoppingCard(c);
	}

	public static void buy(ShoppingCard s) throws NotEnoughMoneyException, CannotShipException {
		double cartPrice = s.getGrossPrice();
		Customer customer = s.getCustomer();

		double difference = customer.getAvailableMoney() - cartPrice;
		if (difference < 0) {
			throw new NotEnoughMoneyException();
		}

		customer.changeMoney(cartPrice * -1);
		availableMoney += cartPrice;

		ffCenter.sendProducts(s);

	}

	public static double getMoney() {
		return availableMoney;
	}

	public static void MAKE_INSOLVENT() {
		availableMoney = 0d;
	}

}