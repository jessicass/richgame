package thoughtworks.fixedAssets;

import java.text.DecimalFormat;

import thoughtworks.MapObject;

public class House extends Space implements MapObject {
	public final String symbol = "2";
	public static final String name = "洋房";
	private final int level = 2;
	private int upgradeFunds;
	private int passToll;
	
	public House(int position){
		super(position);
		this.upgradeFunds = super.getUpgradeFunds();
		this.passToll = super.getPassToll() * Integer.parseInt(
				new DecimalFormat("0").format(Math.pow(2, level)));
	}
	
	@Override
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
		Skyscraper skyscraper = new Skyscraper(position);
		skyscraper.totalCost = totalCost + upgradeFunds;
		skyscraper.owner = owner;
		skyscraper.hasBlock = hasBlock;
		skyscraper.hasBomb = hasBomb;
		return skyscraper;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
