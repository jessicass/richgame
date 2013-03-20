package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.players.Player;

public class UpdatePlayerPositionTest {
	private Game game;
	private ArrayList<Player> players;
	
	@Before
	public void setUp(){
		game = new Game();
		game.CreatPlayerList("12");
		players = game.getPlayerList().getPlayers();
	}
	
	@Test
	public void shouldPlayerPositionBe5(){
		game.updatePlayerPosition(players.get(0), 5);
		assertThat(players.get(0).getPosition(), is(5));
	}
	
	@Test
	public void shouldPlayerPositionBe2(){
		game.updatePlayerPosition(players.get(0), 6);
		while(players.get(0).getPosition() >= 6){
			game.updatePlayerPosition(players.get(0), 6);
		}
		assertThat(players.get(0).getPosition(), is(2));
	}
	
	@Test
	public void shouldPlayerPositionBe3(){
		game.getMap().getMapObjectWithIndex(3).setBlock();
		game.updatePlayerPosition(players.get(0), 5);
		assertThat(players.get(0).getPosition(), is(3));
	}
	
	@Test
	public void shouldPlayerPositionBe14(){
		game.getMap().getMapObjectWithIndex(3).setBomb();
		game.updatePlayerPosition(players.get(0), 5);
		assertThat(players.get(0).getPosition(), is(14));
	}
}
