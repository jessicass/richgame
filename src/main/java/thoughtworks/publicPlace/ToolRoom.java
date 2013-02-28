package thoughtworks.publicPlace;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.command.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.*;

public class ToolRoom implements MapObject {
	public static final String symbol = "T";
	public static final int position = 28;
	public static final int LIMIT_NUMBER_OF_TOOLS = 10;
	public static final String WELCOME = "��ӭ���ٵ����ݣ�������" +
			"������Ҫ�ĵ��ߵı�ţ�";
	public static final String NUMBER_OF_TOOLS_BEYOND_LIMIT = "���Ѿ�ӵ��10������";
	public static final String QUIT_TOOLROOM = "f";
	public static final int MAX_TOOL_NUMBER = 3;
	public static final int MIN_TOOL_NUMBER = 1;
	private boolean hasBlock;
	private boolean hasBomb;
	
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
	
	public static boolean isPointsEnoughToBuyAllTool(int points){
		for(int i = 0; i < MAX_TOOL_NUMBER; i++){
			if(points > buyToolPoints(i + 1)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPointsEnoughToBuyToolWithNumber(
			int points, int toolNumber){
		if(points < buyToolPoints(toolNumber)){
			return false;
		}
		return true;
	}
	
	public static boolean isNumberOfTotalToolsNotBeyondLimits(
			int NumberOfTotalTools) {
		if (NumberOfTotalTools < LIMIT_NUMBER_OF_TOOLS) {
			return true;
		}
		return false;
	}
	
	public String getSymbol(ArrayList<Player> players){
		for(Player player: players){
			if(player.getPosition() == position){
				return player.getShortName();
			}
		}
		if(hasBlock){
			return Block.symbol;
		}
		if(hasBomb){
			return Bomb.symbol;
		}
		return symbol;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setBlock(){
		hasBlock = true;
	}
	
	public void resetBlock(){
		hasBlock = false;
	}
	
	public boolean hasBlock(){
		return hasBlock;
	}
	
	public void setBomb(){
		hasBomb = true;
	}
	
	public void resetBomb(){
		hasBomb = false;
	}
	
	public boolean hasBomb(){
		return hasBomb;
	}

	public void playerPassOnHere(Player passer, Game game) {
		if (isPointsEnoughToBuyAllTool(passer.getPoints())
				&& isNumberOfTotalToolsNotBeyondLimits(passer
						.getToolsOfPlayer().getTotalNumberOfTools())) {
			System.out.println((new ToolInfo()).toolInfoShow());
			System.out.println(WELCOME);
			while (true) {
				String input = Input.getString();
				if (input.matches(QUIT_TOOLROOM)) {
					return;
				}
				if (!Input.isInputAnIntegerInArea(input, MIN_TOOL_NUMBER,
						MAX_TOOL_NUMBER)) {
					continue;
				}
				if (isPointsEnoughToBuyToolWithNumber(passer.getPoints(),
						Integer.valueOf(input))) {
					passer.buyTool(Integer.valueOf(input));
					System.out.println("������߳ɹ���");
					return;
				} else {
					System.out.println("����ǰʣ��ĵ���Ϊ" + passer.getPoints()
							+ "�������Թ���" + Integer.valueOf(input) + "����" + "\n");
				}
			}
		}
	}
	
	public MapObject upgrade(){
		return null;
	}
}
