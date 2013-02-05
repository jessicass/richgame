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
	
	public int getNumberOfSpaces(){
		return numberOfSpaces;
	}
	
	public int getNumberOfCottages(){
		return numberOfCottages;
	}
	
	public int getNumberOfHouses(){
		return numberOfHouses;
	}
	
	public int getNumberOfSkyscrapers(){
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
			spaces.add(space);
			numberOfSpaces++;
			return true;
		}
		return false;
	}
	
	//暂时没用到
	public Space getSpace(int number){
		for(Space space :spaces){
			if(space.getPositionNumber() == number)
				return space;
		}
		return null;
	}
	
	public int getIndexOfSpaces(int number){
		for(Space space :spaces){
			if(space.getPositionNumber() == number)
				return spaces.indexOf(space);
		}
		return -1;
	}
	
	public void upgradeFixedAssets(int number){
		Space spaceUpgrade = getSpace(number);
		if(funds >= spaceUpgrade.getUpgradeFunds()){
			funds = funds-spaceUpgrade.getUpgradeFunds();
			spaces.remove(getIndexOfSpaces(number));
			Space newSpace = spaceUpgrade.upgrade();
			spaces.add(newSpace);
			switch(spaceUpgrade.getLevel()){
		        case 0 :
		    	    numberOfSpaces--;
		    	    numberOfCottages++;
					return;
		        case 1 :
		        	numberOfCottages--;
		    	    numberOfHouses++;
					return;
		        case 2 :
		        	numberOfHouses--;
		    	    numberOfSkyscrapers++;
					return;
		    }
		}
	}
}
