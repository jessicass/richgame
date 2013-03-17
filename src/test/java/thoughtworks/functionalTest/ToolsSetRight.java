package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.Map;
import thoughtworks.command.CommandManager;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class ToolsSetRight {
	private Game game = new Game();
	private Player player;
	private Map map;
	private CommandManager commandManager;
	
	@Before
	public void setUp(){
		game.CreatPlayerList("1234");
		game.getPlayerList().getPlayer(1).updatePosition(3);
		game.getPlayerList().getPlayer(2).updatePosition(4);
		game.getPlayerList().getPlayer(3).updatePosition(5);
		game.getPlayerList().getPlayer(4).updatePosition(6);
		player = game.getPlayerList().getPlayer(1);
		map = game.getMap();
		commandManager = game.getCommandManager();
		map.getMapObjectWithIndex(64).playerPassOnHere(player, game);
		map.getMapObjectWithIndex(64).playerPassOnHere(player, game);
		map.getMapObjectWithIndex(64).playerPassOnHere(player, game);
	}
	
	@Test
	public void shouldToolSetRight(){
		player.buyTool(Block.toolNumber);
		player.buyTool(Block.toolNumber);
		player.buyTool(Bomb.toolNumber);
		commandManager.isCommandRunEnd("block 1", player, game);
		assertThat(map.getMapObjectWithIndex(4).hasBlock(), is(false));
		commandManager.isCommandRunEnd("block 4", player, game);
		assertThat(map.getMapObjectWithIndex(7).hasBlock(), is(true));
		commandManager.isCommandRunEnd("bomb 4", player, game);
		assertThat(map.getMapObjectWithIndex(7).hasBomb(), is(false));
	}
	
	@Test
	public void shouldToolClearRight(){
		player.buyTool(Block.toolNumber);
		player.buyTool(Robot.toolNumber);
		commandManager.isCommandRunEnd("block 4", player, game);
		commandManager.isCommandRunEnd("robot", player, game);
		assertThat(map.getMapObjectWithIndex(7).hasBlock(), is(false));
	}
}
