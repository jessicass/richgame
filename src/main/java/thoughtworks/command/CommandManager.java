package thoughtworks.command;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.GlobalSettings;
import thoughtworks.Map;
import thoughtworks.MapObject;
import thoughtworks.fixedAssets.Space;
import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;
import thoughtworks.tools.Tool;

public class CommandManager {
	private boolean isCommandEnd = true;

	public boolean isCommandRunEnd(String command, Player player, Game game) {
		String[] commandStrings = command.split(" ");
		if (command.toLowerCase().startsWith("block")) {
			setBlockWithCommand(commandStrings[1], player, game);
			return !isCommandEnd;
		}
		if (command.toLowerCase().startsWith("bomb")) {
			setBombWithCommand(commandStrings[1], player, game);
			return !isCommandEnd;

		}
		if (command.equalsIgnoreCase("robot")) {
			setRobotWithCommand(player, game);
			return !isCommandEnd;
		}
		if (command.toLowerCase().startsWith("sell")) {
			sellFixedAssetsWithCommand(commandStrings[1], player, game);
			return !isCommandEnd;
		}
		if (command.toLowerCase().startsWith("sellTool")) {
			sellRoolsWithCommand(commandStrings[1], player);
			return !isCommandEnd;
		}
		if (command.equalsIgnoreCase("query")) {
			System.out.println(Query.queryProperty(player));
			return !isCommandEnd;
		}
		if (command.equalsIgnoreCase("help")) {
			System.out.println(Help.COMMAND_TABLE);
			return !isCommandEnd;
		}
		if (command.equalsIgnoreCase("quit")) {
			System.exit(0);
		}
		if (command.equalsIgnoreCase("roll")) {
			int step = Input.throwDice();
			System.out.println("前进" + step + "步");
			game.updatePlayerPosition(player, step);
			return isCommandEnd;
		}
		System.out.println("指令输入错误！");
		return !isCommandEnd;
	}

	public void setBlockWithCommand(String parameter, Player player, Game game) {
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
		if (isToolPositionSetRight(toolPosition, player, game)) {
			player.getTools().decreaseNumberOfTools(Block.toolNumber);
			game.getMap().getMapObjectWithIndex(toolPosition).setBlock();
			System.out.println("设置故障成功！");
		}
	}

	public void setBombWithCommand(String parameter, Player player, Game game) {
		if (!player.getTools().isOwnToolWithNumberOf(Bomb.toolNumber)) {
			System.out.println("您并不拥有该道具！");
			return;
		}
		if (!Input.isInputAnIntegerInArea(parameter, Bomb.setRange)) {
			return;
		}
		int distance = Integer.parseInt(parameter);
		int toolPosition = PositionUpdate.getCurrentPositionWithDistance(
				player.getPosition(), distance);
		if (isToolPositionSetRight(toolPosition, player, game)) {
			player.getTools().decreaseNumberOfTools(Bomb.toolNumber);
			game.getMap().getMapObjectWithIndex(toolPosition).setBomb();
			System.out.println("设置炸弹成功！");
		}
	}

	public void setRobotWithCommand(Player player, Game game) {
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

	public void sellFixedAssetsWithCommand(String parameter, Player player,
			Game game) {
		if (isFixedAssetPositionRight(parameter, player, game.getMap())) {
			int position = Integer.parseInt(parameter);
			game.sellSpaceWithPositionOf(position);
			System.out.println("出售房产成功！");
		}
	}

	public void sellRoolsWithCommand(String parameter, Player player) {
		if (isToolNumberRight(parameter, player)) {
			int toolNumber = Integer.parseInt(parameter);
			player.sellTool(Tool.createTool(toolNumber));
			System.out.println("出售道具成功！");
		}
	}

	public boolean isFixedAssetPositionRight(String positionString,
			Player player, Map map) {
		if (!Input.isInputAnIntegerInArea(positionString, 0,
				GlobalSettings.MAX_POSITION)) {
			return false;
		}
		int position = Integer.parseInt(positionString);
		Space space = (Space) map.getMapObjectWithIndex(position);
		if (!space.isOwnedBy(player)) {
			System.out.println("该位置的房产不属于您！");
			return false;
		}
		return true;
	}

	private boolean isToolNumberRight(String toolNumberString, Player player) {
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

	private boolean isToolPositionSetRight(int toolPosition, Player player,
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

	private boolean isPositionEqualPlayersPosition(ArrayList<Player> players,
			int position) {
		for (Player player : players) {
			if (player.getPosition() == position) {
				System.out.println("不可放置在有玩家的位置！");
				return true;
			}
		}
		return false;
	}

	private boolean isPositionSetToolAlready(MapObject mapObject) {
		if (mapObject.hasBlock() || mapObject.hasBomb()) {
			System.out.println("该处已有道具！");
			return true;
		}
		return false;
	}
}
