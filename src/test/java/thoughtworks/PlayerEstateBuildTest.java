package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.fixedAssets.Space;

public class PlayerEstateBuildTest {
	private Space space;
	private Player player;
	
	@Before
	public void setUp(){
		Player.INITIAL_FUNDS = 10000;
		space = new Space(2);
		player = new Player(1);
	}
	
	@Test
	public void ownerOfFixedAssetsTest(){
		boolean theOwnerOrNot = player.whetherTheOwnerOf(space);
		assertThat(theOwnerOrNot , is(false));
	}
	
	@Test
	public void shouldAfterBuySpaceFundsRemainBe9800(){
		player.buySpace(space);
		assertThat(player.getFunds(), is(9800));
	}
	
	@Test
	public void shouldAfterUpgradeSpaceFundsRemainBe9600(){
		player.buySpace(space);
		player.upgradeFixedAssets(space);
		assertThat(player.getFunds(), is(9800));
	}
}
