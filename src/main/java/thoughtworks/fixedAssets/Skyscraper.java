package thoughtworks.fixedAssets;

import java.text.DecimalFormat;

public class Skyscraper extends Space {
	public static final String symbol = "3";
	public static final int level = 3;
	protected int passToll;
	
	public Skyscraper(int positionNumber){
		super(positionNumber);
		this.passToll = super.getPassToll() * Integer.parseInt(
				new DecimalFormat("0").format(Math.pow(2, level)));
	}
	
	public int getPassToll(){
		return this.passToll;
	}
}
