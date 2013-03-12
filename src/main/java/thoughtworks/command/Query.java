package thoughtworks.command;

import thoughtworks.fixedAssets.*;
import thoughtworks.players.Player;
import thoughtworks.tools.Tool;
import thoughtworks.tools.ToolInfo;

public class Query {
	public static String queryProperty(Player player){
		String fundInfo = "资金：" + player.getFunds() + "元；"; 
		String pointInfo = "点数：" + player.getPoints() + "点；";
		String fixedAssetInfo =	"地产：" + 
		    Space.name + player.getFixedAssetsOfPlayer().getNumberOfSpaces() + "处；" + 
	        Cottage.name + player.getFixedAssetsOfPlayer().getNumberOfCottages() + "处；" + 
	        House.name + player.getFixedAssetsOfPlayer().getNumberOfHouses() + "处；" + 
	        Skyscraper.name + player.getFixedAssetsOfPlayer().getNumberOfSkyscrapers() + "处；";
		String toolInfo = "道具：";
		for(Tool tool:(new ToolInfo()).getTools()){
			toolInfo += tool.getName() + player.getToolsOfPlayer().getNumberOfTools(
					tool.getToolNumber()) + "个；";
		}
		return fundInfo + "\n" + pointInfo + "\n" + fixedAssetInfo + 
				"\n" + toolInfo + "\n";
	}
}
