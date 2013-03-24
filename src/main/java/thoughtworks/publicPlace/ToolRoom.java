package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.GlobalSettings;
import thoughtworks.MapObject;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.*;

public class ToolRoom extends Terrain implements MapObject {
	public static final String symbol = "T";
	
	public ToolRoom(int position) {
		super(position);
	}
	
	public static boolean isPointsEnoughToBuyAllTool(int points){
		for(int toolNumber = 1; toolNumber < GlobalSettings.MAX_TOOL_NUMBER; toolNumber++){
			Tool tool = Tool.createTool(toolNumber);
			if(points > tool.getBuyPoints()){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPointsEnoughToBuyToolWithNumber(
			int points, Tool tool){
		if(points < tool.getBuyPoints()){
			return false;
		}
		return true;
	}
	
	public static boolean isNumberOfTotalToolsNotBeyondLimits(
			int NumberOfTotalTools) {
		if (NumberOfTotalTools < GlobalSettings.LIMIT_NUMBER_OF_TOOLS) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getSymbol(){
		return symbol;
	}
	
	@Override
	public void playerPassOnHere(Player passer, Game game) {
		if (isPointsEnoughToBuyAllTool(passer.getPoints())
				&& isNumberOfTotalToolsNotBeyondLimits(passer
						.getTools().getTotalNumberOfTools())) {
			System.out.println((new ToolInfo()).toolInfoShow());
			System.out.println(GlobalSettings.WELCOME_TO_TOOLROOM);
			while (true) {
				String input = Input.getString();
				if (input.matches(GlobalSettings.QUIT_TOOLROOM)) {
					return;
				}
				if (!Input.isInputAnIntegerInArea(input, GlobalSettings.MIN_TOOL_NUMBER,
						GlobalSettings.MAX_TOOL_NUMBER)) {
					continue;
				}
				if (isPointsEnoughToBuyToolWithNumber(passer.getPoints(),
						Tool.createTool(Integer.valueOf(input)))) {
					passer.buyTool(Tool.createTool(Integer.valueOf(input)));
					System.out.println("购买道具成功！");
					return;
				} else {
					System.out.println("您当前剩余的点数为" + passer.getPoints()
							+ "，不足以购买" + Integer.valueOf(input) + "道具" + "\n");
				}
			}
		}
	}
}
