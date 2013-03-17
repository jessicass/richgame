package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
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
		assertThat(game.isCreatPlayerListSuccess("12"), is(true));
		players = game.getPlayers();
		assertThat(players.get(0).getName(), is("钱夫人"));
		assertThat(players.get(0).getShortName(), is("Q"));
		assertThat(players.get(1).getName(), is("阿土伯"));
		assertThat(players.get(1).getShortName(), is("A"));
	}
	
	@Test
	public void shouldRoleNumbersBeyondLimit(){
		assertThat(game.isCreatPlayerListSuccess("5"), is(false));
	}
	
	@Test
	public void shouldRoleNumbersRepeat(){
		assertThat(game.isCreatPlayerListSuccess("11"), is(false));
	}
	
	@Test
	public void shouldNumberOfPlayersBeyondLimit(){
		assertThat(game.isCreatPlayerListSuccess("12345"), is(false));
	}
}
