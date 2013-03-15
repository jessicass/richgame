package thoughtworks.players;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FixedAssetsOfPlayerTest {
	private FixedAssetsOfPlayer fixedAssetsOfPlayer;
	
	@Before
	public void setUp(){
		fixedAssetsOfPlayer = new FixedAssetsOfPlayer();
	}
	
	@Test
	public void shouldAfterAddNewSpaceNumberOfSpacesBe1(){
		fixedAssetsOfPlayer.addNewSpace();
		assertThat(fixedAssetsOfPlayer.getNumberOfSpaces(), is(1));
	}
	
	@Test
	public void shouldUpgradeSpaceNumberOfCottagesBe1(){
		shouldAfterAddNewSpaceNumberOfSpacesBe1();
		fixedAssetsOfPlayer.upgradeSpace(0);
		assertThat(fixedAssetsOfPlayer.getNumberOfCottages(), is(1));
	}
	
	@Test
	public void shouldUpgradeCottageNumberOfHousesBe1(){
		shouldUpgradeSpaceNumberOfCottagesBe1();
		fixedAssetsOfPlayer.upgradeSpace(1);
		assertThat(fixedAssetsOfPlayer.getNumberOfHouses(), is(1));
	}
	
	@Test
	public void shouldUpgradeHouseNumberOfSkyscrapersBe1(){
		shouldUpgradeCottageNumberOfHousesBe1();
		fixedAssetsOfPlayer.upgradeSpace(2);
		assertThat(fixedAssetsOfPlayer.getNumberOfSkyscrapers(), is(1));
	}
	
	@Test
	public void shouldSkyscraperCanNotBeUpgraded(){
		shouldUpgradeHouseNumberOfSkyscrapersBe1();
		fixedAssetsOfPlayer.upgradeSpace(3);
		assertThat(fixedAssetsOfPlayer.getNumberOfSkyscrapers(), is(1));	
	}
	
	@Test
	public void shouldAfterSellSpaceNumberOfSpacesBe0(){
		shouldAfterAddNewSpaceNumberOfSpacesBe1();
		fixedAssetsOfPlayer.sellSpace(0);
		assertThat(fixedAssetsOfPlayer.getNumberOfSpaces(), is(0));
	}
	
	@Test
	public void shouldAfterSellCottageNumberOfCottagesBe0(){
		shouldUpgradeSpaceNumberOfCottagesBe1();
		fixedAssetsOfPlayer.sellSpace(1);
		assertThat(fixedAssetsOfPlayer.getNumberOfCottages(), is(0));
	}
	
	@Test
	public void shouldSellHouseNumberOfHousesBe0(){
		shouldUpgradeCottageNumberOfHousesBe1();
		fixedAssetsOfPlayer.sellSpace(2);
		assertThat(fixedAssetsOfPlayer.getNumberOfHouses(), is(0));
	}
	
	@Test
	public void shouldSellSkyscraperNumberOfSkyscrapersBe0(){
		shouldUpgradeHouseNumberOfSkyscrapersBe1();
		fixedAssetsOfPlayer.sellSpace(3);
		assertThat(fixedAssetsOfPlayer.getNumberOfSkyscrapers(), is(0));
	}
}
