package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;

public class Roll extends Command {
	@Override
	public void commandExecute(Player player, Game game) {
		int step = Input.throwDice();
		System.out.println("前进" + step + "步");
		game.updatePlayerPosition(player, step);
	}

}
