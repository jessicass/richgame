package thoughtworks.fixedAssets;

import thoughtworks.MapObject;

public class Space implements MapObject{
	private final String symbol = "0";
	private final int level = 0;
	protected int position;
	private int buyFunds;
	private int upgradeFunds;
	private int passToll;
	protected boolean isOwned;
	
	public Space(int position){
		this.position = position;
		this.isOwned = false;
		if((position>0 && position<14)
				||(position>14 && position<28)){
			buyFunds = 200;
		}
		else if(position>28 && position<35){
			buyFunds = 500;
		}
		else if((position>35 && position<49)
				||(position>49 && position<63)){
			buyFunds = 300;
		}
		upgradeFunds = buyFunds;
		passToll = buyFunds / 2;
	}
	
	public void toBeOwned(){
		isOwned = true;
	}
	
	public boolean isOwned(){
		return isOwned;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getPositionNumber(){
		return position;
	}
	
	public int getBuyFunds(){
		return buyFunds;
	}
	
	public int getUpgradeFunds(){
		return upgradeFunds;
	}
	
	public int getPassToll(){
		return passToll;
	}
	
	public Space upgrade(){
		return new Cottage(position);
	}
}
