package thoughtworks.tools;

public class Tool {
	private String name;
	private String symbol;
	private int toolNumber;
	private int buyPoints;
	private int setRange;
	
	public Tool(String name, String symbol, int toolNumber, 
			int buyPoints, int setRange) {
		this.name = name;
		this.symbol = symbol;
		this.toolNumber = toolNumber;
		this.buyPoints = buyPoints;
		this.setRange = setRange;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public int getToolNumber(){
		return toolNumber;
	}
	
    public int getBuyPoints(){
    	return buyPoints;
    }
    
    public int getSetRange(){
    	return setRange;
    }
}
