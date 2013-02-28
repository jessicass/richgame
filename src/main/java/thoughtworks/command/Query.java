package thoughtworks.command;

import thoughtworks.fixedAssets.*;
import thoughtworks.players.Player;
import thoughtworks.tools.Tool;
import thoughtworks.tools.ToolInfo;

public class Query {
	public static String queryProperty(Player player){
		String fundInfo = "�ʽ�" + player.getFunds() + "Ԫ��"; 
		String pointInfo = "������" + player.getPoints() + "�㣻";
		String fixedAssetInfo =	"�ز���" + 
		    Space.name + player.getFixedAssetsOfPlayer().getNumberOfSpaces() + "����" + 
	        Cottage.name + player.getFixedAssetsOfPlayer().getNumberOfCottages() + "����" + 
	        House.name + player.getFixedAssetsOfPlayer().getNumberOfHouses() + "����" + 
	        Skyscraper.name + player.getFixedAssetsOfPlayer().getNumberOfSkyscrapers() + "����";
		String toolInfo = "���ߣ�";
		for(Tool tool:(new ToolInfo()).getTools()){
			toolInfo += tool.getName() + player.getToolsOfPlayer().getNumberOfTools(
					tool.getToolNumber()) + "����";
		}
		return fundInfo + "\n" + pointInfo + "\n" + fixedAssetInfo + 
				"\n" + toolInfo + "\n";
	}
}
