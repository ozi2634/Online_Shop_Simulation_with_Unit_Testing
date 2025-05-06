package products;

import interfaces.Downloadable;

public class Game extends AbstractProduct implements Downloadable {

	public Game(String name, Price price) {
		super(name, price);
	}

	public static int unitsSold = 0;
	private boolean beenSold = false;
	
	@Override
	public boolean unitSold() {
		return beenSold;
	}

	@Override
	public double getUnitsSold() {
		return unitsSold;
	}
	
	@Override
	public boolean sell() {
		if (beenSold == true) {return false;}
		beenSold = true;
		unitsSold++;
		return true;
	}
	
	@Override
	public boolean returnProduct() {      //basically reset
		if (beenSold == false) {return false;}
		beenSold = false;
		unitsSold--;
		return true;
	}
}
