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
		game.isCreatPlayerListSuccess("1234");
		player = game.getPlayers().get(0);
		game.getMapObjectWithIndex(64).playerPassOnHere(player, game);
	}
	
	@Test
	public void shouldSellBlockObtain50Points(){
		player.buyTool(Block.toolNumber);
		player.sellToolWithNumberOf(Block.toolNumber);
		assertThat(player.getPoints(), is(60));
		assertThat(player.getToolsOfPlayer().getNumberOfTools(Block.toolNumber), is(0));	
	}
	
	@Test
	public void shouldSellRobotObtain30Points(){
		player.buyTool(Robot.toolNumber);
		player.sellToolWithNumberOf(Robot.toolNumber);
		assertThat(player.getPoints(), is(60));
		assertThat(player.getToolsOfPlayer().getNumberOfTools(Robot.toolNumber), is(0));
	}
		
	@Test
	public void shouldSellBombObtain50Points(){
		player.buyTool(Bomb.toolNumber);
		player.sellToolWithNumberOf(Bomb.toolNumber);
		assertThat(player.getPoints(), is(60));
		assertThat(player.getToolsOfPlayer().getNumberOfTools(Bomb.toolNumber), is(0));
	}
}
