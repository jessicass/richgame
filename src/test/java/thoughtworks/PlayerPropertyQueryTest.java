package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Player;

public class PlayerPropertyQueryTest {
	private Player newPlayer;
	
	@Before
	public void setUp(){
		newPlayer = new Player(1);
	}
	
	@Test
	public void shouldPlayerNameTest(){
		assertThat(newPlayer.getPlayerName(), is("«Æ∑Ú»À"));
		assertThat(newPlayer.getShortName(), is("Q"));
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
		assertThat(newPlayer.getFixedAssetsSpace(), is(0));
		assertThat(newPlayer.getFixedAssetsCottage(), is(0));
		assertThat(newPlayer.getFixedAssetsHouse(), is(0));
		assertThat(newPlayer.getFixedAssetsSkyscraper(), is(0));
	}
	
	@Test
	public void shouldInitialToolsBlockBe0(){
		assertThat(newPlayer.getToolsBlock(), is(0));
		assertThat(newPlayer.getToolsBomb(), is(0));
		assertThat(newPlayer.getToolsRobot(), is(0));
	}
}
