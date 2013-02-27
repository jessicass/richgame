package thoughtworks.players;

import thoughtworks.MapObject;
import thoughtworks.PositionUpdate;
import thoughtworks.fixedAssets.*;
import thoughtworks.publicPlace.*;
import thoughtworks.tools.*;

public class Player {
	public static int INITIAL_FUNDS = 10000;
	private int funds;
	private int points=0;
	private String playerName;
	private String shortName;
	private int position;
	private boolean isBombed = false;
	private boolean isOwnerOfLuck = false;
	private boolean isTrappedInPrison = false;
	private int timesToPauseWhenBeBombed = 
			Hospital.timesToPauseWhenPlayerBeHospitalized;
	private int timesForFreeWhenOwnLuck = 5;
	private int timesBeTrappedInPrison = 
			Prison.timesPlayerBeTrappedInPrison;
	
    private FixedAssetsOfPlayer fixedAssetsOfPlayer = 
    		new FixedAssetsOfPlayer();
	private ToolsOfPlayer toolsOfPlayer = new ToolsOfPlayer();

	public Player(int index){
		playerName = PlayerName.getPlayerName(index);
		shortName = PlayerName.getShortName(index);
		funds = Player.INITIAL_FUNDS;
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

	public String queryProperty(){
		String fundInfo = "资金：" + funds + "元；"; 
		String pointInfo = "点数：" + points + "点；";
		String fixedAssetInfo =	"地产：" + 
		    Space.name + fixedAssetsOfPlayer.getNumberOfSpaces() + "处；" + 
	        Cottage.name + fixedAssetsOfPlayer.getNumberOfCottages() + "处；" + 
	        House.name + fixedAssetsOfPlayer.getNumberOfHouses() + "处；" + 
	        Skyscraper.name + fixedAssetsOfPlayer.getNumberOfSkyscrapers() + "处；";
		String toolInfo = "道具：";
		for(Tool tool:(new ToolInfo()).getTools()){
			toolInfo += tool.getName() + toolsOfPlayer.getNumberOfTools(
					tool.getToolNumber()) + "个；";
		}
		return fundInfo + "\n" + pointInfo + "\n" + fixedAssetInfo + 
				"\n" + toolInfo + "\n";
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
	
	public boolean isBankrupt() {
		if((fixedAssetsOfPlayer.getTotalNumberOfFixedAssets() <= 0) && 
		    (funds <= 0)){
			return true;
		}
		return false;
	}
}
