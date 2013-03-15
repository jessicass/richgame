package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.Map;
import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class PlayerSellFixedAssetsTest {
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
		player.buySpace(space.getBuyFunds());
		space.toBeOwned(player);
	}
	
	@Test
	public void shouldSellSpaceObtain400(){
		assertThat(player.getFunds(), is(9800));
		assertThat(space.getTotalCost(), is(200));
		game.sellSpaceWithPositionOf(1);
		assertThat(player.getFunds(), is(10200));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSpaces(), is(0));	
	}
	
	@Test
	public void shouldSellCottageObtain800(){
		game.upgradeFixedAssetsWithPositionOf(1);
		game.sellSpaceWithPositionOf(1);
		assertThat(player.getFunds(), is(10400));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfCottages(), is(0));
	}
		
	@Test
	public void shouldSellHouseObtain1200(){
		game.upgradeFixedAssetsWithPositionOf(1);
		game.upgradeFixedAssetsWithPositionOf(1);
		game.sellSpaceWithPositionOf(1);
		assertThat(player.getFunds(), is(10600));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfHouses(), is(0));
	}
	
	@Test
	public void shouldSellSkyscraperObtain1600(){
		game.upgradeFixedAssetsWithPositionOf(1);
		game.upgradeFixedAssetsWithPositionOf(1);
		game.upgradeFixedAssetsWithPositionOf(1);
		game.sellSpaceWithPositionOf(1);
		assertThat(player.getFunds(), is(10800));
		assertThat(player.getFixedAssetsOfPlayer().getNumberOfSkyscrapers(), is(0));
	}
}
