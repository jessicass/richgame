package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.players.Player;

public abstract class Command {
	public abstract void commandExecute(Player player, Game game);
}
