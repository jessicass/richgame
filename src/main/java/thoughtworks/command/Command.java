package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.players.Player;

public abstract class Command {
	public abstract void commandExecute(String parameter, Player player, Game game);
}
