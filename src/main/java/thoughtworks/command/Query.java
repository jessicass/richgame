package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.fixedAssets.*;
import thoughtworks.players.Player;
import thoughtworks.tools.Tool;
import thoughtworks.tools.ToolInfo;

public class Query extends Command {
	public static String queryProperty(Player player){
		String fundInfo = "资金：" + player.getFunds() + "元；"; 
		String pointInfo = "点数：" + player.getPoints() + "点；";
		String fixedAssetInfo =	"地产：" + 
		    Space.name + player.getFixedAssets().getNumberOfSpaces(0) + "处；" +
	        Cottage.name + player.getFixedAssets().getNumberOfSpaces(1) + "处；" +
	        House.name + player.getFixedAssets().getNumberOfSpaces(2) + "处；" +
	        Skyscraper.name + player.getFixedAssets().getNumberOfSpaces(3) + "处；";
		String toolInfo = "道具：";
		for(Tool tool:(new ToolInfo()).getTools()){
			toolInfo += tool.getName() + player.getTools().getNumberOfTools(
					tool.getToolNumber()) + "个；";
		}
		return fundInfo + "\n" + pointInfo + "\n" + fixedAssetInfo + 
				"\n" + toolInfo + "\n";
	}

	@Override
	public void commandExecute(String parameter, Player player, Game game) {
		System.out.println(queryProperty(player));
	}
}
