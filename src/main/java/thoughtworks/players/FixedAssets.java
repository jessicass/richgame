package thoughtworks.players;

import java.util.ArrayList;

public class FixedAssets {
	private ArrayList<Integer> numberOfSpaces = new ArrayList<Integer>();
	
	public FixedAssets(){
		numberOfSpaces.add(new Integer(0));
		numberOfSpaces.add(new Integer(0));
		numberOfSpaces.add(new Integer(0));
		numberOfSpaces.add(new Integer(0));
	}
	
	public int getNumberOfSpaces(int level){
		if(level > 3)
			return -1;
		return numberOfSpaces.get(level);
	}

	public int getTotalNumberOfFixedAssets() {
		int totalNumber = 0;
		for(int number:numberOfSpaces){
			totalNumber += number;
		}
		return totalNumber;
	}
	
	public void addNewSpace(){
		numberOfSpaces.set(0, numberOfSpaces.get(0) + 1);
	}

	public void upgradeSpace(int level){
		if(level > 2)
			return;
		numberOfSpaces.set(level, numberOfSpaces.get(level) - 1);
		numberOfSpaces.set(level + 1, numberOfSpaces.get(level + 1) + 1);
	}

	public void sellSpace(int level) {
		if(level > 3)
			return;
		numberOfSpaces.set(level, numberOfSpaces.get(level) - 1);
	}
}
