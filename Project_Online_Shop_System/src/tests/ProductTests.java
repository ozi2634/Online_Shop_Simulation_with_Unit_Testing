package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import products.Book;
import products.Price;
import products.Tax;

class ProductTests {

	Book book1 = new Book("Book1", new Price(10, Tax.FULL));
	Book book2 = new Book("Book2", new Price(20, Tax.FULL));

	Book[] Books = new Book[10];

	@BeforeEach
	void setUp() throws Exception {
		for (int i = 0; i < 10; i++) {
			Books[i] = new Book("Book" + i, new Price(i * 3d, i % 2 == 0 ? Tax.FULL : Tax.REDUCED));
		}

		for (int i = 0; i < 10; i++) {
			Books[i].returnProduct();
		}

		book1 = new Book("Book1", new Price(10, Tax.FULL));
		book2 = new Book("Book2", new Price(20, Tax.FULL));
	}

	// Überprüft, ob jedes Book-Objekt im Books-Array den richtigen Namen hat.
	@Test
	void getNameTest() {
		for (int i = 0; i < Books.length; i++) {
			assertTrue(Books[i].getName().equals("Book" + i), "Book had wrong name");
		}

	}

	// Überprüft den Nettopreis jedes Buches.
	@Test
	void getPriceTest() {
		for (int i = 0; i < Books.length; i++) {// net
			assertTrue(Books[i].getPrice().getNetPrice() == i * 3d, "Book had wrong net Price");
		}

		for (int i = 0; i < Books.length; i++) {// gross and tax
			double actual = (Books[i].getPrice().getGrossPrice() * 100d);
			double expected = ((i * 3d + (i % 2 == 0 ? i * 3d * Tax.FULL.tax : i * 3d * Tax.REDUCED.tax)) * 100d);
			assertTrue(expected == actual, "Book had wrong gross Price and/or taxClass");
		}

	}

	// Überprüft die Maße und die Download-Größe jedes Buches.
	@Test
	void getMeasurementsDownloadTest() {// all should be 10
		for (int i = 0; i < Books.length; i++) {// net
			assertTrue(Books[i].getHeight() == 10d, "height");
			assertTrue(Books[i].getLength() == 10d, "length");
			assertTrue(Books[i].getWeight() == 10d, "weight");
			assertTrue(Books[i].getWidth() == 10d, "width");
			assertTrue(Books[i].getDownloadSize() == 10d, "DownloasSize");
		}
	}

	// Testet das Verkaufen (sell), Zurückgeben (returnProduct) und Überprüfen, ob
	// das Buch verkauft wurde (unitSold).
	@Test
	void sellReturnUnitSoldTest() {
		for (int i = 0; i < Books.length; i++) {// selling
			assertTrue(Books[i].sell(), "Should be able to sell");
		}

		for (int i = 0; i < Books.length; i++) {// if instance has been sold
			assertTrue(Books[i].unitSold(), "Should be already sold");
		}

		for (int i = 0; i < Books.length; i++) {// selling sold books
			assertFalse(Books[i].sell(), "Should not be able to sell the book as its already sold");
		}

		assertTrue(Books[0].getUnitsSold() == 10d, "sold books should be 10d");// overall sold

		for (int i = 0; i < Books.length; i++) {// returning
			assertTrue(Books[i].returnProduct(), "Should be able to return Product as its been sold");
		}

		assertTrue(Books[0].getUnitsSold() == 0d, "sold books should be 0d");// overall sold

	}

	// Testet die Vergleichsfunktion (compareTo) der Book-Klasse.
	// Überprüft, ob die Bücher nach Namen korrekt sortiert sind.
	@Test
	void compareTest() {
		assertTrue(book1.compareTo(book2) < 0, "should be <0 as book 2 is more expensive");
		assertTrue(book2.compareTo(book1) > 0, "should be >0 as book 1 is less expensive");

		Random random = new Random();
		for (int i = 0; i < 20; i++) {// Jumbling up the order
			int firstIndex = random.nextInt(0, 10);
			int secondIndex = random.nextInt(0, 10);

			Book temp = Books[firstIndex];
			Books[firstIndex] = Books[secondIndex];
			Books[secondIndex] = temp;
		}

		String str = "";
		for (int i = 0; i < 10; i++) {// testing if it actually got jumbled up
			str += Books[i].getName();
		}
		assertFalse(str.equals("Book0Book1Book2Book3Book4Book5Book6Book7Book8Book9"));

		Arrays.sort(Books);// sorting using the CompareTo method

		for (int i = 0; i < 10; i++) {// all names should be in ascending order, as Books got sorted by Gross Price
										// which increases proportional to book number
			assertEquals(Books[i].getName(), "Book" + i, "Didnt sort right");
		}

	}

}
