package thoughtworks.players;

import thoughtworks.fixedAssets.*;
import thoughtworks.publicPlace.*;
import thoughtworks.tools.*;

public class Player {
	public static int INITIAL_FUNDS = 10000;
	private int funds;
	private int points=0;
	private String playerName;
	private String shortName;
	
    private FixedAssetsOfPlayer fixedAssets = new FixedAssetsOfPlayer();
	private ToolsOfPlayer tools = new ToolsOfPlayer();
	private boolean isOwnerOfLuck = false;
	
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
	
	public int getNumberOfSpaces(){
		return fixedAssets.getNumberOfSpaces();
	}
	
	public int getNumberOfCottages(){
		return fixedAssets.getNumberOfCottages();
	}
	
	public int getNumberOfHouses(){
		return fixedAssets.getNumberOfHouses();
	}
	
	public int getNumberOfSkyscrapers(){
		return fixedAssets.getNumberOfSkyscrapers();
	}
	
	public int getNumberOfBlocks(){
		return tools.getNumberOfTools(Block.toolNumber);
	}
		
	public int getNumberOfRobots(){
		return tools.getNumberOfTools(Robot.toolNumber);
	}
	
	public int getNumberOfBombs(){
		return tools.getNumberOfTools(Bomb.toolNumber);
	}

	public int getTotalNumberOfTools(){
		return tools.getTotalNumberOfTools();
	}
	
	public void buySpace(Space space){
		funds -= space.getBuyFunds();
	    fixedAssets.addNewSpace(space);
	}
	
	public void buyTool(int toolNumber) {
		points -= ToolRoom.buyToolPoints(toolNumber);
		tools.buyTool(toolNumber);
	}
	
	public void chooseGift(int gift){
		switch(gift){
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
	
	public boolean isOwnerOfLuck() {
		return isOwnerOfLuck;
	}
	
	public boolean isOwnerOfSpace(Space space) {
		return fixedAssets.getArrayListOfSpaces().contains(space);
	}
	
	public void upgradeFixedAssets(int positionNumber){
		Space upgradeSpace = fixedAssets.getSpace(positionNumber);
		funds -= upgradeSpace.getUpgradeFunds();
		fixedAssets.upgradeSpace(positionNumber);
	}

	public void handInPassTollToOthers(Space space){
		funds -= space.getPassToll();
	}
	
	public void obtainPassTollFromOthers(Space space){
		funds += space.getPassToll();
	}

	public void obtainPointsFromMine(Mine mine) {
		points += mine.getPoints();
	}
}
