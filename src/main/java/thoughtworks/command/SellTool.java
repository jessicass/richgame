package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.players.Player;
import thoughtworks.tools.Tool;

public class SellTool extends Command {

	@Override
	public void commandExecute(String parameter, Player player, Game game) {
		if (CommandManager.isToolNumberRight(parameter, player)) {
			int toolNumber = Integer.parseInt(parameter);
			player.sellTool(Tool.createTool(toolNumber));
			System.out.println("出售道具成功！");
		}
	}

}
