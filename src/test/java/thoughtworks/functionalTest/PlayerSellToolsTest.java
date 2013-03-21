package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.players.Player;
import thoughtworks.tools.*;

public class PlayerSellToolsTest {
	private Game game = new Game();
	private Player player;
	
	@Before
	public void setUp(){
		game.CreatPlayerList("1234");
		player = game.getPlayerList().getPlayer(1);
		game.getMap().getMapObjectWithIndex(64).playerPassOnHere(player, game);
	}
	
	@Test
	public void shouldSellBlockObtain50Points(){
		player.buyTool(Tool.createTool(Block.toolNumber));
		player.sellTool(Tool.createTool(Block.toolNumber));
		assertThat(player.getPoints(), is(60));
		assertThat(player.getTools().getNumberOfTools(Block.toolNumber), is(0));
	}
	
	@Test
	public void shouldSellRobotObtain30Points(){
		player.buyTool(Tool.createTool(Robot.toolNumber));
		player.sellTool(Tool.createTool(Robot.toolNumber));
		assertThat(player.getPoints(), is(60));
		assertThat(player.getTools().getNumberOfTools(Robot.toolNumber), is(0));
	}
		
	@Test
	public void shouldSellBombObtain50Points(){
		player.buyTool(Tool.createTool(Bomb.toolNumber));
		player.sellTool(Tool.createTool(Bomb.toolNumber));
		assertThat(player.getPoints(), is(60));
		assertThat(player.getTools().getNumberOfTools(Bomb.toolNumber), is(0));
	}
}
