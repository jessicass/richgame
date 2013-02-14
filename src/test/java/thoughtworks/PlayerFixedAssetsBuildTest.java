package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.fixedAssets.*;
import thoughtworks.players.Player;

public class PlayerFixedAssetsBuildTest {
	private Space space;
	private Player player;
	
	@Before
	public void setUp(){
		Player.INITIAL_FUNDS = 10000;
		space = new Space(2);
		player = new Player(1);
	}
	
	@Test
	public void shouldBuySpaceNeed200(){
		player.buySpace(space);
		assertThat(player.getFunds(), is(9800));
		assertThat(player.getNumberOfSpaces(), is(1));
	}
	
	@Test
	public void shouldSpaceCanUpgradeToCottage(){
		shouldBuySpaceNeed200();
		player.upgradeFixedAssets(space.getPositionNumber());
		assertThat(player.getFunds(), is(9600));
		assertThat(player.getNumberOfSpaces(), is(0));
		assertThat(player.getNumberOfCottages(), is(1));
	}
		
	@Test
	public void shouldCottageCanUpgradeToHouse(){
		shouldSpaceCanUpgradeToCottage();
		player.upgradeFixedAssets(space.getPositionNumber());
		assertThat(player.getFunds(), is(9400));
		assertThat(player.getNumberOfHouses(), is(1));
	}
	
	@Test
	public void shouldHouseCanUpgradeToSkyscraper(){
		shouldCottageCanUpgradeToHouse();
		player.upgradeFixedAssets(space.getPositionNumber());
		assertThat(player.getFunds(), is(9200));
		assertThat(player.getNumberOfSkyscrapers(), is(1));
	}
}
