package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;

public class SetBlock extends Command {
	@Override
	public void commandExecute(String parameter, Player player, Game game) {
			if (!player.getTools().isOwnToolWithNumberOf(Block.toolNumber)) {
				System.out.println("您并不拥有该道具！");
				return;
			}
			if (!Input.isInputAnIntegerInArea(parameter, Block.setRange)) {
				return;
			}
			int distance = Integer.parseInt(parameter);
			int toolPosition = PositionUpdate.getCurrentPositionWithDistance(
					player.getPosition(), distance);
			if (CommandManager.isToolPositionSetRight(toolPosition, player, game)) {
				player.getTools().decreaseNumberOfTools(Block.toolNumber);
				game.getMap().getMapObjectWithIndex(toolPosition).setBlock();
				System.out.println("设置故障成功！");
			}
		}
}
