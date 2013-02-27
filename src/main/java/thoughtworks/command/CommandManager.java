package thoughtworks.command;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.Input;
import thoughtworks.Map;
import thoughtworks.MapObject;
import thoughtworks.PositionUpdate;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class CommandManager {
	public static boolean isCommandEnd = true ;
	
	public static boolean isCommandRunEnd(String command, Player player, Game game){
		String[] commandStrings = command.split(" ");
		if (command.toLowerCase().startsWith("block")) {
			setBlockWithCommand(commandStrings[1], player, game);
		}
		if (command.toLowerCase().startsWith("bomb")) {
			setBombWithCommand(commandStrings[1], player, game);
		}
		if (command.equalsIgnoreCase("robot")) {
			setRobotWithCommand(player, game);
		}
    	if (command.toLowerCase().startsWith("sell")) {
    		sellFixedAssetsWithCommand(commandStrings[1], player, game);
		}
    	if (command.toLowerCase().startsWith("sellTool")) {
    		sellRoolsWithCommand(commandStrings[1], player, game);
		}
		if (command.equalsIgnoreCase("query")) {
			System.out.println(player.queryProperty());
		}
		if (command.equalsIgnoreCase("help")) {
			System.out.println(Help.COMMAND_TABLE);
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
		return !isCommandEnd;
	}
    
	public static void setBlockWithCommand(String parameter, Player player, Game game){
		if (isDistanceSetToolRight(parameter, player, game)) {
			if (player.isOwnToolWithNumberOf(Block.toolNumber)) {
				player.useTool(Block.toolNumber);
				int distance = Integer.parseInt(parameter);
				int setPosition = PositionUpdate.getSetPositionWithDistance(
						player.getPosition(), distance);
				game.getMapObjectWithIndex(setPosition).setBlock();
			}
		}
	}
	
	public static void setBombWithCommand(String parameter, Player player, Game game){
		if (isDistanceSetToolRight(parameter, player, game)) {
			if (player.isOwnToolWithNumberOf(Bomb.toolNumber)) {
				player.useTool(Bomb.toolNumber);
				int distance = Integer.parseInt(parameter);
				int setPosition = PositionUpdate.getSetPositionWithDistance(
						player.getPosition(), distance);
				game.getMapObjectWithIndex(setPosition).setBomb();
			}
		}
	}
	
	public static void setRobotWithCommand(Player player, Game game){
		if (player.isOwnToolWithNumberOf(Robot.toolNumber)) {
			player.useTool(Robot.toolNumber);
			int backPosition = PositionUpdate.getSetPositionWithDistance(
					player.getPosition(), -Robot.clearRange);
			MapObject mapObject = game.getMapObjectWithIndex(backPosition);
			for (int i = 0; i < Robot.clearRange * 2 + 1; i++) {
				if (mapObject.hasBlock()) {
					mapObject.resetBlock();
				}
				if (mapObject.hasBomb()) {
					mapObject.resetBomb();
				}
				mapObject = game.getMapObjectWithIndex(PositionUpdate.getNextPosition(
						mapObject.getPosition()));
			}
		}
	}
	
	public static void sellFixedAssetsWithCommand(String parameter, Player player, Game game){
		if (isFixedAssetPositionRight(parameter, player)) {
			int position = Integer.parseInt(parameter);
			game.sellSpaceWithPositionOf(position);
		}
	}
	
	public static void sellRoolsWithCommand(String parameter, Player player, Game game){
		if (isToolNumberRight(parameter, player)) {
			int toolNumber = Integer.parseInt(parameter);
			player.sellToolWithNumberOf(toolNumber);
		}
	}
	
	public static boolean isFixedAssetPositionRight(String positionString,
			Player player) {
		if(!Input.isInputAnIntegerInArea(positionString, 0, Map.MAX_POSITION)){
			return false;
		}
		int position = Integer.parseInt(positionString);
		if(!player.isOwnerOfSpace(position)){
			return false;
		}
		return true;
	}
	
    public static boolean isToolNumberRight(String toolNumberString,
			Player player) {
		if(!Input.isInputAnIntegerInArea(toolNumberString, 1, 3)){
			return false;
		}
		int toolNumber = Integer.parseInt(toolNumberString);
		if(!player.isOwnToolWithNumberOf(toolNumber)){
			return false;
		}
		return true;
	}

	public static boolean isDistanceSetToolRight(String distanceString, Player player, Game game){
		if(!Input.isInputAnIntegerInArea(distanceString, Block.setRange, 
				-Block.setRange)){
			return false;
		}
		int distance = Integer.parseInt(distanceString);
		int setPosition = PositionUpdate.getSetPositionWithDistance(
				player.getPosition(), distance);
		if(isPositionEqualPlayersPosition(game.getPlayers(), 
				setPosition)){
			return false;
		}
		if(isPositionSetToolAlready(game.getMap(), setPosition)){
			return false;
		}
		return true;
	}

	public static boolean isPositionEqualPlayersPosition(
			ArrayList<Player> players, int position){
		for(Player player: players){
			if(player.getPosition() == position){
				System.out.println("不可放置在有玩家的位置！");
				return true;
			}
		}
		return false;
	}

	private static boolean isPositionSetToolAlready(Map map, int setPosition) {
		if(map.getMapObjectWithIndex(setPosition).hasBlock() || 
				map.getMapObjectWithIndex(setPosition).hasBomb()){
			System.out.println("该处已有道具！");
			return true;
		}
		return false;
	}
}
