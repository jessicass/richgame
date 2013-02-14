package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.players.Player;

public class PlayerPropertyQueryTest {
	private Player newPlayer;
	
	@Before
	public void setUp(){
		newPlayer = new Player(1);
	}
	
	@Test
	public void shouldInitialFundsBe10000(){
		assertThat(newPlayer.getFunds(), is(10000));
	}
	
	@Test
	public void shouldInitialPointsBe0(){
		assertThat(newPlayer.getPoints(), is(0));
	}
	
	@Test
	public void shouldInitialFixedAssetsSpaceBe0(){
		assertThat(newPlayer.getNumberOfSpaces(), is(0));
		assertThat(newPlayer.getNumberOfCottages(), is(0));
		assertThat(newPlayer.getNumberOfHouses(), is(0));
		assertThat(newPlayer.getNumberOfSkyscrapers(), is(0));
	}
	
	@Test
	public void shouldInitialToolsBlockBe0(){
		assertThat(newPlayer.getNumberOfBlocks(), is(0));
		assertThat(newPlayer.getNumberOfBombs(), is(0));
		assertThat(newPlayer.getNumberOfRobots(), is(0));
	}
}
