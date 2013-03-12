package thoughtworks.playersTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.Map;
import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class PlayerTest {
	private Game game = new Game();
	private Player player;
	private Map map;
	
	@Before
	public void setUp(){
		game.isCreatPlayerListSuccess(1234);
		player = game.getPlayers().get(0);
		map = game.getMap();
	}
	
	@Test
	public void shouldBuySpaceNeed200(){
		player.buySpace((Space)map.getMapObjectWithIndex(1));
		assertThat(player.getFunds(), is(9800));
		assertThat(player.getFixedAssetsOfPlayer().getSpace(1).getTotalCost(), is(200));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSpaces(), is(1));
	}
	
	@Test
	public void shouldSpaceCanUpgradeToCottage(){
		shouldBuySpaceNeed200();
		game.upgradeFixedAssetsWithPositionOf(1);
		assertThat(player.getFunds(), is(9600));
		assertThat(player.getFixedAssetsOfPlayer().getSpace(1).getTotalCost(), is(400));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSpaces(), is(0));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfCottages(), is(1));
	}
		
	@Test
	public void shouldCottageCanUpgradeToHouse(){
		shouldSpaceCanUpgradeToCottage();
		game.upgradeFixedAssetsWithPositionOf(1);
		assertThat(player.getFunds(), is(9400));
		assertThat(player.getFixedAssetsOfPlayer().getSpace(1).getTotalCost(), is(600));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfHouses(), is(1));
	}
	
	@Test
	public void shouldHouseCanUpgradeToSkyscraper(){
		shouldCottageCanUpgradeToHouse();
		game.upgradeFixedAssetsWithPositionOf(1);
		assertThat(player.getFunds(), is(9200));
		assertThat(player.getFixedAssetsOfPlayer().getSpace(1).getTotalCost(), is(800));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSkyscrapers(), is(1));
	}
}
