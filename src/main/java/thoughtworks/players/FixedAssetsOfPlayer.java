package thoughtworks.players;

import java.util.ArrayList;

import thoughtworks.MapObject;
import thoughtworks.fixedAssets.Space;

public class FixedAssetsOfPlayer {
	private ArrayList<Space> spaces = new ArrayList<Space>();
	private int numberOfSpaces = 0;
	private int numberOfCottages = 0;
	private int numberOfHouses = 0;
	private int numberOfSkyscrapers = 0;
	
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
	

	public int getTotalNumberOfFixedAssets() {
		return spaces.size();
	}
	
	public ArrayList<Space> getArrayListOfSpaces(){
		return spaces;
	}
	
	public int getIndexOfSpaceInSpaces(int position){
		for(Space space :spaces){
			if(space.getPosition() == position)
				return spaces.indexOf(space);
		}
		return -1;
	}

	public Space getSpace(int position){
		return spaces.get(getIndexOfSpaceInSpaces(position));
	}
	
	public void addNewSpace(Space space){
		spaces.add(space);
		numberOfSpaces++;
		space.toBeOwned();
	}

	public void upgradeSpace(MapObject mapObject){
		Space oldSpace = getSpace(mapObject.getPosition());
		spaces.set(getIndexOfSpaceInSpaces(mapObject.getPosition()), (Space)mapObject);
		switch(oldSpace.getLevel()){
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

	public void deleteSelledSpace(int position) {
		Space oldSpace = getSpace(position);
		spaces.remove(oldSpace);
		switch(oldSpace.getLevel()){
	        case 0 :
	    	    numberOfSpaces--;
				return;
	        case 1 :
	        	numberOfCottages--;
				return;
	        case 2 :
	        	numberOfHouses--;
				return;
	        case 3 :
	        	numberOfSkyscrapers--;
				return;
		}
	}
}
