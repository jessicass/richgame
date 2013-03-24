package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.players.Player;
import thoughtworks.tools.Robot;

public class SetRobot extends Command {
	@Override
	public void commandExecute(String command, Player player, Game game) {
		if (player.getTools().isOwnToolWithNumberOf(Robot.toolNumber)) {
			player.getTools().decreaseNumberOfTools(Robot.toolNumber);
			int backPosition = PositionUpdate.getCurrentPositionWithDistance(
					player.getPosition(), -Robot.clearRange);
			MapObject mapObject = game.getMap().getMapObjectWithIndex(
					backPosition);
			for (int i = 0; i < Robot.clearRange * 2 + 1; i++) {
				if (mapObject.hasBlock()) {
					mapObject.resetBlock();
				}
				if (mapObject.hasBomb()) {
					mapObject.resetBomb();
				}
				mapObject = game.getMap()
						.getMapObjectWithIndex(
								PositionUpdate.getNextPosition(mapObject
										.getPosition()));
			}
			System.out.println("清扫道具成功！");
		}


	}
}
