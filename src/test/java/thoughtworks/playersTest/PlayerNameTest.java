package thoughtworks.playersTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import thoughtworks.players.PlayerName;

public class PlayerNameTest {
	@Test
	public void shouldShortNameBeQWithIndex1(){
		assertThat(PlayerName.getShortName(1), is("Q"));
	}
	
	@Test
	public void shouldNameBeQianWithIndex1(){
		assertThat(PlayerName.getPlayerName(1), is("钱夫人"));
	}
	
	@Test
	public void shouldShortNameBeAWithIndex2(){
		assertThat(PlayerName.getShortName(2), is("A"));
	}
	
	@Test
	public void shouldNameBeAtuWithIndex2(){
		assertThat(PlayerName.getPlayerName(2), is("阿土伯"));
	}
	
	@Test
	public void shouldShortNameBeSWithIndex3(){
		assertThat(PlayerName.getShortName(3), is("S"));
	}
	
	@Test
	public void shouldNameBeSunWithIndex3(){
		assertThat(PlayerName.getPlayerName(3), is("孙小美"));
	}
	
	@Test
	public void shouldShortNameBeJWithIndex4(){
		assertThat(PlayerName.getShortName(4), is("J"));
	}
	
	@Test
	public void shouldNameBeJinWithIndex4(){
		assertThat(PlayerName.getPlayerName(4), is("金贝贝"));
	}
	
	

}
