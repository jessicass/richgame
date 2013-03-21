package thoughtworks.tools;

public class Bomb extends Tool {
	public static final int toolNumber = 3;
	public static final int buyPoints = 50;
	public static final String symbol = "@";
	public static final int setRange = 10;
	public static final String name = "炸弹";
	
	public Bomb(){
		super(name, symbol, toolNumber, buyPoints, setRange);
	}
}
