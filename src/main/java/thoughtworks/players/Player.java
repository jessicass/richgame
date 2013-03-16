package thoughtworks.players;

import java.awt.Color;

import thoughtworks.Map;
import thoughtworks.fixedAssets.*;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.publicPlace.*;

public class Player {
	public static int INITIAL_FUNDS = 10000;
	public static int INITIAL_MIN_FUNDS = 500;
	public static int INITIAL_MAX_FUNDS = 50000;
	public Color color;
	private int funds;
	private int points=0;
	private String playerName;
	private String shortName;
	private int position;
	private boolean isBombed = false;
	private boolean isOwnerOfLuck = false;
	private boolean isTrappedInPrison = false;
	private boolean isBankrupt = false;
	private int timesToPauseWhenBeBombed = 
			Hospital.timesToPauseWhenPlayerBeHospitalized + 1;
	private int timesForFreeWhenOwnLuck = 5 + 1;
	private int timesBeTrappedInPrison = 
			Prison.timesPlayerBeTrappedInPrison + 1;
	
    private FixedAssetsOfPlayer fixedAssetsOfPlayer = 
    		new FixedAssetsOfPlayer();
	private ToolsOfPlayer toolsOfPlayer = new ToolsOfPlayer();

	public Player(int index){
		playerName = DefaultPlayer.getPlayerName(index);
		shortName = DefaultPlayer.getShortName(index);
		funds = Player.INITIAL_FUNDS;
		switch (index) {
		case 1:
			color = Color.RED;
			break;
		case 2:
			color = Color.YELLOW;
			break;
		case 3:
			color = Color.BLUE;
			break;
		case 4:
			color = Color.GREEN;
			break;
		}
	}
	
	public Color getColor(){
		return color;
	}
	
	public int getFunds(){
		return funds;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public String getShortName(){
		return shortName;
	}
	
	public int getPosition(){
		return position;
	}
	
	public FixedAssetsOfPlayer getFixedAssetsOfPlayer(){
		return fixedAssetsOfPlayer;
	}
	
	public ToolsOfPlayer getToolsOfPlayer(){
		return toolsOfPlayer;
	}

	public void updatePosition(int position){
		this.position = position;
	}

	public void updatePositionWithStep(int step){
		position = PositionUpdate.getCurrentPositionWithDistance(position, step);
	}

	public void buySpace(int buyFunds){
		funds -= buyFunds;
		fixedAssetsOfPlayer.addNewSpace();
	}
	
	public void sellSpace(Space space){
		funds += space.getTotalCost() * 2;
		fixedAssetsOfPlayer.sellSpace(space.getLevel());
	}
	
	public void buyTool(int toolNumber) {
		points -= ToolRoom.buyToolPoints(toolNumber);
		toolsOfPlayer.buyTool(toolNumber);
	}
	
	public void chooseGift(int giftNumber){
		switch(giftNumber){
	        case 1:
	        	funds += GiftRoom.presentFunds;
	        	return;
	        case 2:
	        	points += GiftRoom.presentPoints;
	    	    return;
	        case 3:
	        	isOwnerOfLuck = true;
	        	return;
	    }
	}
	
	public void decreaseLuckyTimes(){
		if(isOwnerOfLuck){
			timesForFreeWhenOwnLuck--;
		    if(timesForFreeWhenOwnLuck == 0){
		    	isOwnerOfLuck = false;
		    	timesForFreeWhenOwnLuck = 5 + 1;
		    }
		}
	}
	
	public void decreaseHospitalizedTimes(){
		if(isBombed){
			timesToPauseWhenBeBombed--;
		    if(timesToPauseWhenBeBombed == 0){
		    	isBombed = false;
		    	timesToPauseWhenBeBombed = 
		    			Hospital.timesToPauseWhenPlayerBeHospitalized + 1;
		    }
		} 
	}
	
	public void decreaseTrappedInPrisonTimes(){
		if(isTrappedInPrison){
			timesBeTrappedInPrison--;
		    if(timesBeTrappedInPrison == 0){
		    	isTrappedInPrison = false;
		    	timesBeTrappedInPrison = 
		    			Prison.timesPlayerBeTrappedInPrison + 1;
		    }
		}
	}
	
	public void toBeBombed(){
		isBombed = true;
		position = Map.HosipitalPosition;
	}
	
	public void toBeTrappedInPrison(){
		isTrappedInPrison = true;
	}
	
	public boolean isBombed(){
		return isBombed;
	}
	
	public boolean isOwnerOfLuck() {
		return isOwnerOfLuck;
	}
	
	public boolean isTrappedInPrison() {
		return isTrappedInPrison;
	}
	
	public void upgradeOwnFixedAssets(Space space){
		int upgradeFunds = space.getUpgradeFunds();
		funds -= upgradeFunds;
		fixedAssetsOfPlayer.upgradeSpace(space.getLevel());
	}
    
	public void handInPassTollToOthers(int passToll){
		funds -= passToll;
	}
	
	public void obtainPassTollFromOthers(int passToll){
		funds += passToll;
	}

	public void obtainPointsFromMine(int points) {
		this.points += points;
	}

	public void sellToolWithNumberOf(int toolNumber) {
		this.points += toolsOfPlayer.getPointsOfToolWithNumberOf(toolNumber);
		toolsOfPlayer.decreaseNumberOfTools(toolNumber);
	}
	
	public void testBankrupt(int passTool) {
		if((fixedAssetsOfPlayer.getTotalNumberOfFixedAssets() <= 0) && 
		    (funds < passTool)){
			isBankrupt = true;
		}
	}

	public boolean isBankrupt() {
		return isBankrupt;
	}
}
