package thoughtworks.players;

import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class ToolsOfPlayer {
	private int numberOfBlocks;
	private int numberOfBombs;
	private int numberOfRobots;
	
	public int getNumberOfTools(int toolNumber){
		switch(toolNumber){
		    case Block.toolNumber:
		    	return numberOfBlocks;
		    case Robot.toolNumber:
		    	return numberOfBombs;
		    case Bomb.toolNumber:
		    	return numberOfRobots;
		}
		return -1;
	}
	
	public int getTotalNumberOfTools(){
		return numberOfBlocks + numberOfBombs + numberOfRobots;
	}
	
	public void buyTool(int toolNumber) {
		switch(toolNumber){
		    case Block.toolNumber:
		    	numberOfBlocks++;
	    	    return;
	        case Robot.toolNumber:
	        	numberOfBombs++;
	        	return;
	        case Bomb.toolNumber:
	        	numberOfRobots++;
	        	return;
		}
	}
}
