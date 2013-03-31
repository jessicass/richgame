package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.players.Player;

public class SellFixedAsset extends Command {
	private String parameter;
	
	public SellFixedAsset(String paremeter){
		this.parameter = paremeter;
	}
	
	@Override
	public void commandExecute(Player player, Game game) {
		if (CommandManager.isFixedAssetPositionRight(parameter, player, game.getMap())) {
			int position = Integer.parseInt(parameter);
			game.sellSpaceWithPositionOf(position);
			System.out.println("出售房产成功！");
		}
	}
}
