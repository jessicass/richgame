package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.Map;
import thoughtworks.fixedAssets.*;
import thoughtworks.players.Player;

public class PlayerFixedAssetsBuildTest {
	private Game game = new Game();
	private Player player;
	private Map map;
	private Space space;
	
	@Before
	public void setUp(){
		game.isCreatPlayerListSuccess(1234);
		player = game.getPlayers().get(0);
		map = game.getMap();
		space = (Space)map.getMapObjectWithIndex(1);
	}
	
	@Test
	public void shouldBuySpaceNeed200(){
		player.buySpace(space.getBuyFunds());
		space.toBeOwned(player);
		assertThat(player.getFunds(), is(9800));
		assertThat(space.getTotalCost(), is(200));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSpaces(), is(1));
	}
	
	@Test
	public void shouldSpaceCanUpgradeToCottage(){
		shouldBuySpaceNeed200();
		game.upgradeFixedAssetsWithPositionOf(1);
		space = (Space)map.getMapObjectWithIndex(1);
		assertThat(player.getFunds(), is(9600));
		assertThat(space.getTotalCost(), is(400));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSpaces(), is(0));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfCottages(), is(1));
	}
	
	@Test
	public void shouldCottageCanUpgradeToHouse(){
		shouldSpaceCanUpgradeToCottage();
		game.upgradeFixedAssetsWithPositionOf(1);
		space = (Space)map.getMapObjectWithIndex(1);
		assertThat(player.getFunds(), is(9400));
		assertThat(space.getTotalCost(), is(600));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfHouses(), is(1));
	}
	
	@Test
	public void shouldHouseCanUpgradeToSkyscraper(){
		shouldCottageCanUpgradeToHouse();
		game.upgradeFixedAssetsWithPositionOf(1);
		space = (Space)map.getMapObjectWithIndex(1);
		assertThat(player.getFunds(), is(9200));
		assertThat(space.getTotalCost(), is(800));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSkyscrapers(), is(1));
	}
}
