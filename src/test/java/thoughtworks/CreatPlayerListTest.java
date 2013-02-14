package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.players.Player;

public class CreatPlayerListTest {
	private Game game;
	private ArrayList<Player> players;
	
	@Before
	public void setUp(){
		game = new Game();
	}
	
	@Test
	public void shouldPlayerShortNameBeQAndA(){
		game.isCreatPlayerListSuccess("12");
		players = game.getPlayers();
		assertThat(players.get(0).getPlayerName(), is("«Æ∑Ú»À"));
		assertThat(players.get(0).getShortName(), is("Q"));
		assertThat(players.get(1).getPlayerName(), is("∞¢Õ¡≤Æ"));
		assertThat(players.get(1).getShortName(), is("A"));
	}
}
