package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.*;

public class ToolRoom extends Terrain implements MapObject {
	public static final String symbol = "T";
	public static final int LIMIT_NUMBER_OF_TOOLS = 10;
	public static final String WELCOME = "欢迎光临道具屋，请输入" +
			"您所需要的道具的编号：";
	public static final String NUMBER_OF_TOOLS_BEYOND_LIMIT = "您已经拥有10个道具";
	public static final String QUIT_TOOLROOM = "f";
	public static final int MAX_TOOL_NUMBER = 3;
	public static final int MIN_TOOL_NUMBER = 1;
	
	public ToolRoom(int position) {
		super(position);
	}
	
	public static boolean isPointsEnoughToBuyAllTool(int points){
		for(int toolNumber = 1; toolNumber < MAX_TOOL_NUMBER; toolNumber++){
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
		if (NumberOfTotalTools < LIMIT_NUMBER_OF_TOOLS) {
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
