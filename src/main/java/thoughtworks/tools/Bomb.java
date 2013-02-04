package thoughtworks.tools;

public class Bomb {
	private final int buyPoints = 50;
	private final String symbol = "@";
	private final int setRange = 10;
	
	public String getSymbol(){
		return symbol;
	}
	
	public int getBuyPoints(){
		return buyPoints;
	}
	
	public int getSetRange(){
		return setRange;
	}	
}
