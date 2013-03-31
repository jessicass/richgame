package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.players.Player;
import thoughtworks.tools.Bomb;

public class SetBomb extends Command {
	private String parameter;
	
	public SetBomb(String paremeter){
		this.parameter = paremeter;
	}
	
	@Override
	public void commandExecute(Player player, Game game) {
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
		if (CommandManager.isToolPositionSetRight(toolPosition, player, game)) {
			player.getTools().decreaseNumberOfTools(Bomb.toolNumber);
			game.getMap().getMapObjectWithIndex(toolPosition).setBomb();
			System.out.println("设置炸弹成功！");
		}


	}

}
