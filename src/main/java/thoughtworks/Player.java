package thoughtworks;

import java.util.ArrayList;

import thoughtworks.fixedAssets.*;
import thoughtworks.tools.*;

public class Player {
	public static int INITIAL_FUNDS;
	private int funds;
	private int points=0;
	private String playerName;
	private String shortName;
	private int numberOfSpaces;
	private int numberOfCottages;
	private int numberOfHouses;
	private int numberOfSkyscrapers;
	
	private ArrayList<Space> spaces = new ArrayList<Space>();
	
	public static final int limitNumberOfTools = 10;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	private ArrayList<Robot> robots = new ArrayList<Robot>(); 
	
	public Player(int index){
		playerName = PlayerName.getPlayerName(index);
		shortName = PlayerName.getShortName(index);
		funds = INITIAL_FUNDS;
		numberOfSpaces = 0;
		numberOfCottages = 0;
		numberOfHouses = 0;
		numberOfSkyscrapers = 0;
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
	
	public int getFixedAssetsSpace(){
		return numberOfSpaces;
	}
	
	public int getFixedAssetsCottage(){
		return numberOfCottages;
	}
	
	public int getFixedAssetsHouse(){
		return numberOfHouses;
	}
	
	public int getFixedAssetsSkyscraper(){
		return numberOfSkyscrapers;
	}
	
	public int getToolsBlock(){
		return blocks.size();
	}
	
	public int getToolsBomb(){
		return bombs.size();
	}
	
	public int getToolsRobot(){
		return robots.size();
	}
	
	public boolean whetherTheOwnerOf(Space space){
		return spaces.contains(space);
	}
	
	public boolean buySpace(Space space){
		if(funds >= space.getBuyFunds()){
			funds = funds-space.getBuyFunds();
			return true;
		}
		return false;
	}
	
	//////////upgrade
	public boolean upgradeFixedAssets(Space space){
		if(funds >= space.getUpgradeFunds()){
			return true;
		}
		return false;
	}
}
