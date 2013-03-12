package thoughtworks.players;

import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class ToolsOfPlayer {
	private int numberOfBlocks;
	private int numberOfRobots;
	private int numberOfBombs;
	
	public int getNumberOfTools(int toolNumber){
		switch(toolNumber){
		    case Block.toolNumber:
		    	return numberOfBlocks;
		    case Robot.toolNumber:
		    	return numberOfRobots;
		    case Bomb.toolNumber:
		    	return numberOfBombs;
		}
		return -1;
	}
	
	public int getTotalNumberOfTools(){
		return numberOfBlocks + numberOfRobots + numberOfBombs;
	}
	
	public void buyTool(int toolNumber) {
		switch(toolNumber){
		    case Block.toolNumber:
		    	numberOfBlocks++;
	    	    return;
	        case Robot.toolNumber:
	        	numberOfRobots++;
	        	return;
	        case Bomb.toolNumber:
	        	numberOfBombs++;
	        	return;
		}
	}
	
	public void useTool(int toolNumber) {
		switch (toolNumber) {
		case Block.toolNumber:
			numberOfBlocks--;
			break;
		case Robot.toolNumber:
			numberOfRobots--;
			break;
		case Bomb.toolNumber:
			numberOfBombs--;
			break;
		}
	}

	public boolean isOwnToolWithNumberOf(int toolNumber){
		switch(toolNumber){
	    case Block.toolNumber:
	    	if(numberOfBlocks > 0){
	    		return true;
	    	}
	    	break;
        case Robot.toolNumber:
        	if(numberOfRobots > 0){
	    		return true;
	    	}
	    	break;
        case Bomb.toolNumber:
        	if(numberOfBombs > 0){
	    		return true;
	    	}
	    	break;
	    }
		System.out.println("道具数量不足！");
		return false;
	}

	public void sellToolWithNumberOf(int toolNumber) {
		switch (toolNumber) {
		case Block.toolNumber:
			numberOfBlocks--;
			break;
		case Robot.toolNumber:
			numberOfRobots--;
			break;
		case Bomb.toolNumber:
			numberOfBombs--;
			break;
		}
	}

	public int getPointsOfToolWithNumberOf(int toolNumber) {
		switch (toolNumber) {
		case Block.toolNumber:
			return Block.buyPoints;
		case Robot.toolNumber:
			return Robot.buyPoints;
		case Bomb.toolNumber:
			return Bomb.buyPoints;
		}
		return 0;
	}
}
