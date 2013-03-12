package thoughtworks.fixedAssets;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.command.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Space implements MapObject{
	public static final String WHETHER_BUY_SPACE = "是否购买该处空地，";
	public static final String WHETHER_UPGRADE_SPACE = "是否升级该处地产，";
	public static final String END_OF_HINT = "元（Y/N）？";
	
	public final String symbol = "0";
	public static final String name = "空地";
	public final int level = 0;
	protected int position;
	private int buyFunds;
	private int upgradeFunds;
	private int passToll;
	
	protected boolean isOwned;
	protected boolean hasBlock;
	protected boolean hasBomb;
	protected int totalCost = 0;
	
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
	
	public void increaseTotalCost(int funds){
		totalCost += funds;
	}
	
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
		totalCost = buyFunds;		
		isOwned = true;
	}
	
	public boolean isOwned(){
		return isOwned;
	}
	
	public void playerPassOnHere(Player passer, Game game){
		if(passer.getFunds() >= buyFunds){
			System.out.println(WHETHER_BUY_SPACE + 
					buyFunds + END_OF_HINT);
			while (true) {
				String input = Input.getString();
				if (input.equalsIgnoreCase("Y")) {
					passer.buySpace(this);
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
	
	public boolean isSafeForPlayerPassOnOtherSpace(Player passer, Game game){
		if(passer.isOwnerOfLuck()){
			System.out.println("福神附身，可免过路费");
			passer.decreasLuckyTimes();
			return true;
		}
		if(game.getTheOwnerOfSpace(position).isBombed() || 
				game.getTheOwnerOfSpace(position).isTrappedInPrison()){
			return true;
		}
		while(true){
			if(passer.getFunds() >= passToll){
				System.out.println("支付过路费给" + game.getTheOwnerOfSpace(
						position).getPlayerName());
				passer.handInPassTollToOthers(passToll);
				game.getTheOwnerOfSpace(position).obtainPassTollFromOthers(
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
		cottage.isOwned = isOwned;
		cottage.hasBlock = hasBlock;
		cottage.hasBomb = hasBomb;
		return cottage;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
