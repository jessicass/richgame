package thoughtworks.command;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.GlobalSettings;
import thoughtworks.Map;
import thoughtworks.MapObject;
import thoughtworks.fixedAssets.Space;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;

public class CommandManager {
	private boolean isCommandEnd = true;
	private Command command;

	public boolean isCommandRunEnd(String commandString, Player player, Game game) {
		String[] commandStrings = commandString.split(" ");
		command = CommandFactory.createCommand(commandStrings[0].toLowerCase());
		if (command == null) {
			return !isCommandEnd;
		}
		command.commandExecute(commandStrings[1], player, game);
		if (command instanceof Roll) {
			return isCommandEnd;
		}
		return !isCommandEnd;
	}

	public static boolean isFixedAssetPositionRight(String positionString,
			Player player, Map map) {
		if (!Input.isInputAnIntegerInArea(positionString, 0,
				GlobalSettings.MAX_POSITION)) {
			return false;
		}
		
		int position = Integer.parseInt(positionString);
		if (!map.isSpaceWithPositionOf(position)) {
			return false;
		}
			
		Space space = (Space) map.getMapObjectWithIndex(position);
		if (!space.isOwnedBy(player)) {
			System.out.println("该位置的房产不属于您！");
			return false;
		}
		return true;
	}

	public static boolean isToolNumberRight(String toolNumberString, Player player) {
		if (!Input.isInputAnIntegerInArea(toolNumberString, 1, 3)) {
			return false;
		}
		int toolNumber = Integer.parseInt(toolNumberString);
		if (!player.getTools().isOwnToolWithNumberOf(toolNumber)) {
			System.out.println("您没有该编号的道具！");
			return false;
		}
		return true;
	}

	public static boolean isToolPositionSetRight(int toolPosition, Player player,
			Game game) {
		if (isPositionEqualPlayersPosition(game.getPlayerList().getPlayers(),
				toolPosition)) {
			return false;
		}
		if (isPositionSetToolAlready(game.getMap().getMapObjectWithIndex(
				toolPosition))) {
			return false;
		}
		return true;
	}

	public static boolean isPositionEqualPlayersPosition(ArrayList<Player> players,
			int position) {
		for (Player player : players) {
			if (player.getPosition() == position) {
				System.out.println("不可放置在有玩家的位置！");
				return true;
			}
		}
		return false;
	}

	public static boolean isPositionSetToolAlready(MapObject mapObject) {
		if (mapObject.hasBlock() || mapObject.hasBomb()) {
			System.out.println("该处已有道具！");
			return true;
		}
		return false;
	}
}
