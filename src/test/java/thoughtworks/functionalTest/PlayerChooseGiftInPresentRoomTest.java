package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.players.Player;

public class PlayerChooseGiftInPresentRoomTest {
	private Player player;
	
	@Before
	public void setUp(){
		player =new Player(1);
	}
	
	@Test
	public void shouldChooseGiftOneObtain2000InFunds(){
		player.chooseGift(1);
		assertThat(player.getFunds(), is(12000));
	}
	
	@Test
	public void shouldChoosePresentTwoObtain200InPoints(){
		player.chooseGift(2);
		assertThat(player.getPoints(), is(200));
	}
	
	@Test
	public void shouldChoosePresentThreeObtainLucky(){
		player.chooseGift(3);
		assertThat(player.isOwnerOfLuck() , is(true));
	}
}
