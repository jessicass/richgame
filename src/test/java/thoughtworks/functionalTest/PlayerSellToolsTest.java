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
		game.getMapObjectWithIndex(64).playerPassOnHere(player, game);
	}
	
	@Test
	public void shouldSellBlockObtain50Points(){
		player.buyTool(Block.toolNumber);
		player.sellTool(Block.toolNumber);
		assertThat(player.getPoints(), is(60));
		assertThat(player.getTools().getNumberOfTools(Block.toolNumber), is(0));
	}
	
	@Test
	public void shouldSellRobotObtain30Points(){
		player.buyTool(Robot.toolNumber);
		player.sellTool(Robot.toolNumber);
		assertThat(player.getPoints(), is(60));
		assertThat(player.getTools().getNumberOfTools(Robot.toolNumber), is(0));
	}
		
	@Test
	public void shouldSellBombObtain50Points(){
		player.buyTool(Bomb.toolNumber);
		player.sellTool(Bomb.toolNumber);
		assertThat(player.getPoints(), is(60));
		assertThat(player.getTools().getNumberOfTools(Bomb.toolNumber), is(0));
	}
}
