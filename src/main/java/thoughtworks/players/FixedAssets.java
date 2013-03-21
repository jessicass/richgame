package thoughtworks.players;

import java.util.ArrayList;

import thoughtworks.functionClass.Input;

public class FixedAssets {
	private ArrayList<Integer> numberOfSpaces = new ArrayList<Integer>();
	
	public FixedAssets(){
		numberOfSpaces.add(new Integer(0));
		numberOfSpaces.add(new Integer(0));
		numberOfSpaces.add(new Integer(0));
		numberOfSpaces.add(new Integer(0));
	}
	
	public int getNumberOfSpaces(int level){
		if(Input.isIntegerInArea(level, 0, 3))
			return numberOfSpaces.get(level);
		return -1;
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
		if(!Input.isIntegerInArea(level, 0, 2))
			return;
		numberOfSpaces.set(level, numberOfSpaces.get(level) - 1);
		numberOfSpaces.set(level + 1, numberOfSpaces.get(level + 1) + 1);
	}

	public void sellSpace(int level) {
		if(!Input.isIntegerInArea(level, 0, 3))
			return;
		numberOfSpaces.set(level, numberOfSpaces.get(level) - 1);
	}
}
