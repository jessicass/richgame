package thoughtworks.tools;

public class Block extends Tool {
	public static final int toolNumber = 1;
	public static final int buyPoints = 50;
	public static final String symbol = "#";
	public static final int setRange = 10;	
	public static final String name = "路障";
	
	public Block(){
		super(name, symbol, toolNumber, buyPoints, setRange);
	}
}
