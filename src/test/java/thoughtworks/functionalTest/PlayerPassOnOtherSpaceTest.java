package thoughtworks.functionalTest;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import thoughtworks.Game;
import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class PlayerPassOnOtherSpaceTest {
	private Game game;
	private Player owner;
	private Player passer;
	private Space space;
	
	@Before
	public void setUp(){
		game = new Game();
		game.CreatPlayerList("12");
		owner = game.getPlayerList().getPlayer(1);
		passer = game.getPlayerList().getPlayer(2);
		space = (Space)game.getMapObjectWithIndex(1);
		owner.buySpace(space.getBuyFunds());
		space.toBeOwned(owner);
	}
	
	@Test
	public void shouldFundsReduce100PassingSpaceOfOthers(){
		space.isSafeForPlayerPassOnOtherSpace(passer);
		assertThat(owner.getFunds(), is(10000-200+100));
		assertThat(passer.getFunds(), is(10000-100));
	}
	
	@Test
	public void shouldPassingSpaceOfOthersForFreeWithLuck(){
		passer.chooseGift(3);
		space.isSafeForPlayerPassOnOtherSpace(passer);
		assertThat(owner.getFunds(), is(9800));
		assertThat(passer.getFunds(), is(10000));
	}
	
	@Test
	public void shouldPassingSpaceOfOthersForFreeWhenOwnerBeBombed(){
		owner.toBeBombed();
		space.isSafeForPlayerPassOnOtherSpace(passer);
		assertThat(owner.getFunds(), is(9800));
		assertThat(passer.getFunds(), is(10000));
	}
	
	@Test
	public void shouldPassingSpaceOfOthersForFreeWhenOwnerBeTrapped(){
		owner.toBeTrappedInPrison();
		space.isSafeForPlayerPassOnOtherSpace(passer);
		assertThat(owner.getFunds(), is(9800));
		assertThat(passer.getFunds(), is(10000));
	}
}
