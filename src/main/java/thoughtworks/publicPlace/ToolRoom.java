package thoughtworks.publicPlace;

import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.*;

public class ToolRoom implements MapObject {
	public static final String symbol = "T";
	public static final int position = 28;
	public static final int LIMIT_NUMBER_OF_TOOLS = 10;
	public static final String WELCOME = 
			"欢迎光临道具屋，请选择您所需要的道具：";
	public static final String NUMBER_OF_TOOLS_BEYOND_LIMIT = 
			"您已经拥有10个道具";
	public static final int MAX_TOOL_NUMBER = 3;
	
	public static int buyToolPoints(int toolNumber) {
		switch(toolNumber){
		    case Block.toolNumber:
	    	    return Block.buyPoints;
	        case Robot.toolNumber:
	        	return Robot.buyPoints;
	        case Bomb.toolNumber:
	        	return Bomb.buyPoints;
		}
		return -1;
	}
	
	public static boolean isPointsOfPlayerEnough(Player player){
		for(int i = 0; i < MAX_TOOL_NUMBER; i++){
			if(player.getPoints() > buyToolPoints(i + 1)){
				return true;
			}
		}
		return false;
	}
	
	public static String playerBuyTool(Player player , int toolNumber){
		if(player.getTotalNumberOfTools() == LIMIT_NUMBER_OF_TOOLS){
			return NUMBER_OF_TOOLS_BEYOND_LIMIT;
		}
		else if(player.getPoints() < buyToolPoints(toolNumber)){
			return "您当前剩余的点数为" + player.getPoints() + 
					"，不足以购买" + toolNumber + "道具";
		}
		else
			return null;
	}
	
	public String getSymbol(){
		return symbol;
	}
}
