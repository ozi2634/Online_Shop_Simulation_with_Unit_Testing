package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import products.Book;
import products.Price;
import products.Tax;
import shop.CannotShipException;
import shop.Customer;
import shop.NotEnoughMoneyException;
import shop.Shop;
import shop.ShoppingCard;

class ShopTests {

	Shop shop;
	ShoppingCard ShopCard0;

	@BeforeEach
	void setUp() {
		shop = new Shop();

		Shop.MAKE_INSOLVENT();

		ShopCard0 = Shop.newShoppingCard(new Customer());
	}

	@Test
	void addRemoveToFromCardTest() {
		assertTrue(ShopCard0.getGrossPrice() == 0, "Price should be 0");

		Book b0 = new Book("b0", new Price(10d, Tax.FULL));
		Book b1 = new Book("b1", new Price(10d, Tax.FULL));
		Book b2 = new Book("b2", new Price(10d, Tax.FULL));
		Book b3 = new Book("b3", new Price(10d, Tax.FULL));

		ShopCard0.addProduct(b0);
		ShopCard0.addProduct(b1);
		ShopCard0.addProduct(b2);
		ShopCard0.addProduct(b3);
		assertTrue(ShopCard0.getProducts().size() == 4, "Size should be 3");
		ShopCard0.removeProduct(b0);
		ShopCard0.removeProduct(b1);
		ShopCard0.removeProduct(b2);
		ShopCard0.removeProduct(b3);
		assertTrue(ShopCard0.getProducts().size() == 0, "Size should be 0");
		assertFalse(ShopCard0.addProduct(null), "should not be able to add null");
		try {
			ShopCard0.removeProduct(null);
			fail("should not be able to remove null");
		} catch (NullPointerException e) {

		}

	}

	@Test
	void priceCardTest() {

		Book b0 = new Book("b0", new Price(10d, Tax.FULL));
		Book b1 = new Book("b1", new Price(10d, Tax.FULL));
		Book b2 = new Book("b2", new Price(10d, Tax.FULL));
		Book b3 = new Book("b3", new Price(10d, Tax.REDUCED));
		Book b4 = new Book("b4", new Price(10d, Tax.REDUCED));
		Book b5 = new Book("b5", new Price(10d, Tax.REDUCED));

		ShopCard0.addProduct(b0);
		ShopCard0.addProduct(b1);
		ShopCard0.addProduct(b2);
		assertEquals(ShopCard0.getGrossPrice(), (10d + 10d * 0.19) * 3, "Price should be 35.7");
		ShopCard0.addProduct(b3);
		ShopCard0.addProduct(b4);
		ShopCard0.addProduct(b5);
		assertEquals(ShopCard0.getGrossPrice(), (10d + 10d * 0.19d) * 3 + (10d + 10d * 0.07d) * 3,
				"Price should be 67.8");
		ShopCard0.removeProduct(b0);
		ShopCard0.removeProduct(b1);
		ShopCard0.removeProduct(b2);
		ShopCard0.removeProduct(b3);
		ShopCard0.removeProduct(b4);
		ShopCard0.removeProduct(b5);
		assertTrue(ShopCard0.getGrossPrice() == 0, "Price should be 0");
	}

	@Test
	void buyEnoughMoneyTest() {
		Book b0 = new Book("b0", new Price(10d, Tax.FULL));
		Book b1 = new Book("b1", new Price(10d, Tax.REDUCED));
		Book b2 = new Book("b2", new Price(10d, Tax.REDUCED));

		ShopCard0.addProduct(b0);
		ShopCard0.addProduct(b1);
		ShopCard0.addProduct(b2);

		Customer currentCustomer = ShopCard0.getCustomer();
		currentCustomer.changeMoney(100d);

		assertTrue(currentCustomer.getAvailableMoney() == 100d, "Money should be 100d");

		try {
			Shop.buy(ShopCard0);
		} catch (NotEnoughMoneyException e) {
			fail("should have been able to buy");
		} catch (CannotShipException e) {

		}

		assertTrue(ShopCard0.getProducts().size() == 0, "size should be 0");

		assertTrue(currentCustomer.getAvailableMoney() == 66.7d, "Money should be 66.7d");

		assertEquals(Shop.getMoney(), 33.3d, "Money in shop should be 33.3d");

		assertTrue(currentCustomer.getProducts().contains(b0), "should own b0");
		assertTrue(currentCustomer.getProducts().contains(b1), "should own b1");
		assertTrue(currentCustomer.getProducts().contains(b2), "should own b2");

		assertTrue(currentCustomer.getProducts().size() == 3, "Size should be 3");

		assertTrue(((Book) (currentCustomer.getProducts().get(0))).unitSold() == true, "should be sold");
		assertTrue(((Book) (currentCustomer.getProducts().get(1))).unitSold() == true, "should be sold");
		assertTrue(((Book) (currentCustomer.getProducts().get(2))).unitSold() == true, "should be sold");
	}

	@Test
	void buyNotEnoughMoneyTest() {
		Book b0 = new Book("b0", new Price(10d, Tax.FULL));
		Book b1 = new Book("b1", new Price(10d, Tax.REDUCED));
		Book b2 = new Book("b2", new Price(10d, Tax.REDUCED));

		ShopCard0.addProduct(b0);
		ShopCard0.addProduct(b1);
		ShopCard0.addProduct(b2);

		Customer currentCustomer = ShopCard0.getCustomer();
		currentCustomer.changeMoney(5d);

		assertTrue(currentCustomer.getAvailableMoney() == 5d, "Money should be 5d");

		try {
			Shop.buy(ShopCard0);
			fail("should not have been able to buy");
		} catch (NotEnoughMoneyException e) {

		} catch (CannotShipException e) {

		}

		assertEquals(Shop.getMoney(), 0d, "Money in shop should be 0d");
		assertTrue(currentCustomer.getAvailableMoney() == 5d, "Money should be 5d");
	}

	@Test
	void unitsSoldTest() {
		Book b0 = new Book("b0", new Price(10d, Tax.FULL));
		Book b1 = new Book("b1", new Price(10d, Tax.REDUCED));
		Book b2 = new Book("b2", new Price(10d, Tax.REDUCED));

		ShopCard0.addProduct(b0);
		ShopCard0.addProduct(b1);
		ShopCard0.addProduct(b2);

		ShopCard0.getCustomer().changeMoney(100d);

		try {
			Shop.buy(ShopCard0);
		} catch (NotEnoughMoneyException e) {
			fail("should have been able to buy");
		} catch (CannotShipException e) {

		}

		assertEquals(Book.unitsSold, 3, "3 should have been sold");

	}

}
