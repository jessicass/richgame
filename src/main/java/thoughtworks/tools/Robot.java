package thoughtworks.tools;

public class Robot extends Tool {
	public static final int toolNumber = 2;
	public static final int buyPoints = 30;
	public static final String symbol = "&";
	public static final int clearRange = 10;
	public static final String name = "机器娃娃";
	
	public String getName(){
		return name;
	}
	
	public int getToolNumber() {
		return toolNumber;
	}

	public int getBuyPoints() {
		return buyPoints;
	}

	public String getSymbol() {
		return symbol;
	}
}
