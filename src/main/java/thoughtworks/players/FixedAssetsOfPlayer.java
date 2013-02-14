package thoughtworks.players;

import java.util.ArrayList;

import thoughtworks.fixedAssets.Space;

public class FixedAssetsOfPlayer {
	private ArrayList<Space> spaces = new ArrayList<Space>();
	private int numberOfSpaces;
	private int numberOfCottages;
	private int numberOfHouses;
	private int numberOfSkyscrapers;
	
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
	
	public ArrayList<Space> getArrayListOfSpaces(){
		return spaces;
	}
	
	public int getIndexOfSpaceInSpaces(int positionNumber){
		for(Space space :spaces){
			if(space.getPositionNumber() == positionNumber)
				return spaces.indexOf(space);
		}
		return -1;
	}
	
	public Space getSpace(int positionNumber){
		return spaces.get(getIndexOfSpaceInSpaces(positionNumber));
	}
	
	public void addNewSpace(Space space){
		spaces.add(space);
		numberOfSpaces++;
		space.toBeOwned();
	}
	
	public void upgradeSpace(int positionNumber){
		Space upgradeSpace = getSpace(positionNumber);
		spaces.remove(upgradeSpace);
		spaces.add(upgradeSpace.upgrade());
		switch(upgradeSpace.getLevel()){
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
