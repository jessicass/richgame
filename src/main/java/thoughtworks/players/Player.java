package thoughtworks.players;

import java.awt.Color;

import thoughtworks.MapObject;
import thoughtworks.command.PositionUpdate;
import thoughtworks.fixedAssets.*;
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
		playerName = PlayerName.getPlayerName(index);
		shortName = PlayerName.getShortName(index);
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
		position = PositionUpdate.getSetPositionWithDistance(position, step);
	}
	
	public void buySpace(Space space){
		funds -= space.getBuyFunds();
		fixedAssetsOfPlayer.addNewSpace(space);
	}

	public void sellSpace(int position){
		funds += fixedAssetsOfPlayer.getSpace(position).getTotalCost() * 2;
		fixedAssetsOfPlayer.deleteSelledSpace(position);
	}
	
	public void buyTool(int toolNumber) {
		points -= ToolRoom.buyToolPoints(toolNumber);
		toolsOfPlayer.buyTool(toolNumber);
	}

	public void useTool(int toolNumber) {
		toolsOfPlayer.useTool(toolNumber);
	}
	
	public boolean isOwnToolWithNumberOf(int toolNumber){
		return toolsOfPlayer.isOwnToolWithNumberOf(toolNumber);
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
	
	public void decreasLuckyTimes(){
		if(isOwnerOfLuck){
		    timesForFreeWhenOwnLuck--;
		    if(timesForFreeWhenOwnLuck == 0){
		    	isOwnerOfLuck = false;
			    timesForFreeWhenOwnLuck = 5;
		    }
		}
	}
	
	public void decreasHospitalizedTimes(){
		if(isBombed){
			timesToPauseWhenBeBombed--;
		    if(timesToPauseWhenBeBombed == 0){
			    isBombed = false;
			    timesToPauseWhenBeBombed = Hospital.
			    		timesToPauseWhenPlayerBeHospitalized;
		    }
		}
	}
	
	public void decreasTrappedInPrisonTimes(){
		if(isTrappedInPrison){
	    	timesBeTrappedInPrison--;
		    if(timesBeTrappedInPrison == 0){
			    isTrappedInPrison = false;
			    timesBeTrappedInPrison = Prison.
			    		timesPlayerBeTrappedInPrison;
		    }
		}
	}
	
	public void toBeBombed(){
		isBombed = true;
		position = Hospital.position;
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
	
	public boolean isOwnerOfSpace(int spacePosition) {
		for(Space space: fixedAssetsOfPlayer.getArrayListOfSpaces()){
			if(space.getPosition() == spacePosition){
				return true;
			}
		}
		return false;
	}
	
	public void upgradeOwnFixedAssets(MapObject mapObject){
		int upgradeFunds = fixedAssetsOfPlayer.getSpace(
				mapObject.getPosition()).getUpgradeFunds();
		funds -= upgradeFunds;
		fixedAssetsOfPlayer.upgradeSpace(mapObject);
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
		toolsOfPlayer.sellToolWithNumberOf(toolNumber);
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
