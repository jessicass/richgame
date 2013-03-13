package thoughtworks.fixedAssets;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Space implements MapObject{
	public static final String WHETHER_BUY_SPACE = "是否购买该处空地，";
	public static final String WHETHER_UPGRADE_SPACE = "是否升级该处地产，";
	public static final String END_OF_HINT = "元（Y/N）？";
	
	public final String symbol = "0";
	public static final String name = "空地";
	private final int level = 0;
	private int buyFunds;
	private int upgradeFunds;
	private int passToll;
	
	protected int position;
	protected boolean hasBlock;
	protected boolean hasBomb;
	protected int totalCost = 0;
	protected Player owner;
	
	public int getTotalCost(){
		return totalCost;
	}
	
	public String getSymbol(ArrayList<Player> players){
		for(Player player: players){
			if(player.getPosition() == position){
				return player.getShortName();
			}
		}
		if(hasBlock){
			return Block.symbol;
		}
		if(hasBomb){
			return Bomb.symbol;
		}
		return symbol;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getPosition(){
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
	
	public Player getOwner(){
		return owner;
	}
	
	public void increaseTotalCost(int funds){
		totalCost += funds;
	}
	
	public Space(int position){ 
		this.position = position;
		this.owner = null;
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
	
	public void toBeOwned(Player player){
		totalCost = buyFunds;		
		owner = player;
	}
	
	public boolean isOwned(){
		return owner != null;
	}
	
	public boolean isOwnedBy(Player player){
		return owner == player;
	}
	
	public void playerPassOnHere(Player passer, Game game){
		if(passer.getFunds() >= buyFunds){
			System.out.println(WHETHER_BUY_SPACE + 
					buyFunds + END_OF_HINT);
			while (true) {
				String input = Input.getString();
				if (input.equalsIgnoreCase("Y")) {
					passer.buySpace(buyFunds);
					toBeOwned(passer);
					System.out.println("购买空地成功！");
					return;
				}
				if (input.equalsIgnoreCase("N")) {
					return;
				}
				System.out.println("请输入（Y/N）：");
			}
		}
	}

	public boolean isPlayerToUpgradeOwnSpace(Player passer){
		if(passer.getFunds() >= upgradeFunds && level < 3){
			System.out.println(WHETHER_UPGRADE_SPACE + 
					upgradeFunds + END_OF_HINT);
			while (true) {
				String input = Input.getString();
				if (input.equalsIgnoreCase("Y")) {
					return true;
				}
				if (input.equalsIgnoreCase("N")) {
					return false;
				}else {
					System.out.println("请输入（Y/N）：");
				}
			}
		}
		return false;
	}
	
	public boolean isSafeForPlayerPassOnOtherSpace(Player passer){
		if(passer.isOwnerOfLuck()){
			System.out.println("福神附身，可免过路费");
			return true;
		}
		if(owner.isBombed() || owner.isTrappedInPrison()){
			return true;
		}
		while(true){
			if(passer.getFunds() >= passToll){
				System.out.println("支付过路费给" + owner.getPlayerName());
				passer.handInPassTollToOthers(passToll);
				owner.obtainPassTollFromOthers(
						passToll);
				return true;
			}
		    else{
			    System.out.println("资金不够付过路费!");
			    return false;
		    }
		}
	}
	
	public void setBlock(){
		hasBlock = true;
	}
	
	public void resetBlock(){
		hasBlock = false;
	}
	
	public boolean hasBlock(){
		return hasBlock;
	}
	
	public void setBomb(){
		hasBomb = true;
	}
	
	public void resetBomb(){
		hasBomb = true;
	}
	
	public boolean hasBomb(){
		return hasBomb;
	}
	
	public MapObject upgrade(){
		Cottage cottage = new Cottage(position);
		cottage.totalCost = totalCost + upgradeFunds;
		cottage.owner = owner;
		cottage.hasBlock = hasBlock;
		cottage.hasBomb = hasBomb;
		return cottage;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
