package thoughtworks.fixedAssets;

import java.text.DecimalFormat;
import thoughtworks.MapObject;

public class House extends Space implements MapObject {
	private final String symbol = "2";
	private final int level = 2;
	private int upgradeFunds;
	private int passToll;
	
	public House(int position){
		super(position);
		this.upgradeFunds = super.getUpgradeFunds();
		this.passToll = super.getPassToll() * Integer.parseInt(
				new DecimalFormat("0").format(Math.pow(2, level)));
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getUpgradeFunds(){
		return this.upgradeFunds;
	}
	
	public int getPassToll(){
		return this.passToll;
	}
	
	public Space upgrade(){
		return new Skyscraper(position);
	}
}
