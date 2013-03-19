package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.PlayerList;

public class CreatPlayerListTest {
	private PlayerList playerList;
	
	@Before
	public void setUp(){
		playerList = new PlayerList(new int[]{1,2,3,4});
	}
	
	@Test
	public void shouldPlayerShortNameBeQAndA(){
		assertThat(PlayerList.isCreatPlayerListSuccess("12"), is(true));
		assertThat(playerList.getPlayer(1).getName(), is("钱夫人"));
		assertThat(playerList.getPlayer(1).getShortName(), is("Q"));
		assertThat(playerList.getPlayer(2).getName(), is("阿土伯"));
		assertThat(playerList.getPlayer(2).getShortName(), is("A"));
	}
	
	@Test
	public void shouldRoleNumbersBeyondLimit(){
		assertThat(PlayerList.isCreatPlayerListSuccess("5"), is(false));
	}
	
	@Test
	public void shouldRoleNumbersRepeat(){
		assertThat(PlayerList.isCreatPlayerListSuccess("11"), is(false));
	}
	
	@Test
	public void shouldNumberOfPlayersBeyondLimit(){
		assertThat(PlayerList.isCreatPlayerListSuccess("12345"), is(false));
	}
}
