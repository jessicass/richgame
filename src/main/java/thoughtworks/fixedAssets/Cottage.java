package thoughtworks.fixedAssets;

import java.text.DecimalFormat;

import thoughtworks.MapObject;

public class Cottage extends Space implements MapObject {
	public final String symbol = "1";
	public static final String name = "茅屋";
	private final int level = 1;
	private int upgradeFunds;
	private int passToll;
	
	public Cottage(int position){
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
		House house = new House(position);
		house.totalCost = totalCost + upgradeFunds;
		house.owner = owner;
		house.hasBlock = hasBlock;
		house.hasBomb = hasBomb;
		return house;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
