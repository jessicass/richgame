package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.players.Player;

public class SellFixedAsset extends Command {

	@Override
	public void commandExecute(String parameter, Player player, Game game) {
		if (CommandManager.isFixedAssetPositionRight(parameter, player, game.getMap())) {
			int position = Integer.parseInt(parameter);
			game.sellSpaceWithPositionOf(position);
			System.out.println("出售房产成功！");
		}
	}
}
