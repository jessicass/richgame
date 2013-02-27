package thoughtworks.fixedAssets;

import thoughtworks.Game;
import thoughtworks.Input;
import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Space implements MapObject{
	public static final String WHETHER_BUY_SPACE = "�Ƿ���ô��յأ�";
	public static final String WHETHER_UPGRADE_SPACE = "�Ƿ������ô��ز���";
	public static final String END_OF_HINT = "Ԫ��Y/N����";
	
	public final String symbol = "0";
	public static final String name = "�յ�";
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
	
	public String getSymbol(){
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
				if (Input.getChar() == 'Y') {
					passer.buySpace(this);
					return;
				}
				if (Input.getChar() == 'N') {
					return;
				}
				System.out.println("�����루Y/N����");
			}
		}
	}

	public boolean isPlayerToUpgradeOwnSpace(Player passer){
		if(passer.getFunds() >= upgradeFunds && level < 3){
			System.out.println(WHETHER_UPGRADE_SPACE + 
					upgradeFunds + END_OF_HINT);
			while (true) {
				if (Input.getChar() == 'Y') {
					return true;
				}
				if (Input.getChar() == 'N') {
					return false;
				}
				System.out.println("�����루Y/N����");
			}
		}
		return false;
	}
	
	public boolean isSafeForPlayerPassOnOtherSpace(Player passer, Game game){
		if(passer.isOwnerOfLuck()){
			System.out.println("�����������·��");
			passer.decreasLuckyTimes();
			return true;
		}
		if(game.getTheOwnerOfSpace(position).isBombed() || 
				game.getTheOwnerOfSpace(position).isTrappedInPrison()){
			return true;
		}
		while(true){
			if(passer.getFunds() >= passToll){
				passer.handInPassTollToOthers(passToll);
				game.getTheOwnerOfSpace(position).obtainPassTollFromOthers(
						passToll);
				return true;
			}
		    else{
			    System.out.println("�ʽ𲻹�����·�ѣ���ѡ��Ҫ���۵ķ�����");
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
