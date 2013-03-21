package thoughtworks.players;

import java.util.ArrayList;
import thoughtworks.functionClass.Input;

public class Tools {
	private ArrayList<Integer> numberOfTools = new ArrayList<Integer>();
	
	public Tools(){
		numberOfTools.add(new Integer(0));
		numberOfTools.add(new Integer(0));
		numberOfTools.add(new Integer(0));
	}
	
	public int getNumberOfTools(int toolNumber){
		if(Input.isIntegerInArea(toolNumber, 1, 3))
			return numberOfTools.get(toolNumber - 1);
		return -1;
	}
	
	public int getTotalNumberOfTools(){
		int totalNumber = 0;
		for(int number:numberOfTools){
			totalNumber += number;
		}
		return totalNumber;
	}
	
	public void buyTool(int toolNumber) {
		if(!Input.isIntegerInArea(toolNumber, 1, 3))
			return;
		numberOfTools.set(toolNumber - 1, numberOfTools.get(toolNumber - 1) + 1);
	}
	
	public boolean isOwnToolWithNumberOf(int toolNumber){
		if(!Input.isIntegerInArea(toolNumber, 1, 3))
			return false;
		if(numberOfTools.get(toolNumber - 1) > 0)
			return true;
		System.out.println("道具数量不足！");
		return false;
	}

	public void decreaseNumberOfTools(int toolNumber) {
		if(!Input.isIntegerInArea(toolNumber, 1, 3))
			return;
		numberOfTools.set(toolNumber - 1, numberOfTools.get(toolNumber - 1) - 1);
	}
}
