package thoughtworks.publicPlace;

import thoughtworks.MapObject;

public class GiftRoom implements MapObject {
	public static final String symbol = "G";
	public static final int position = 35;
	public static final String WELCOME = 
			"欢迎光临道具屋，请选择您所需要的道具：";
	public static final int presentFunds = 2000;
	public static final int presentPoints = 200;
	
	public String getSymbol(){
		return symbol;
	}
}
