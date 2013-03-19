package thoughtworks.players;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.fixedAssets.Space;
import thoughtworks.publicPlace.Mine;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class PlayerTest {
    private Player player;
    private Space space;
    private Mine mine;

    @Test
    public void shouldBuySpaceNeed200() {
    	//given
    	player = new Player(1);
    	space = new Space(1);

    	//when
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);
        
        //then
        assertThat(player.getFunds(), is(9800));
    }

    @Test
    public void shouldUpgradeSpaceToCottageNeed200() {
    	//given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
        
    	//when
    	player.upgradeOwnFixedAssets(space);
        
        //then
        assertThat(player.getFunds(), is(9600));
    }

    @Test
    public void shouldUpgradeCottageToHouseNeed200() {
        //given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
        
    	//when
    	player.upgradeOwnFixedAssets(space);
        
        //then
        assertThat(player.getFunds(), is(9400));
    }

    @Test
    public void shouldUpgradeHouseToSkyscraperNeed200() {
        //given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
    	
    	//when
    	player.upgradeOwnFixedAssets(space);
        
        //then
        assertThat(player.getFunds(), is(9200));
    }

    @Test
    public void shouldSellSpaceObtain400() {
    	//given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);
        
    	//when
    	player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10200));
    }

    @Test
    public void shouldSellCottageObtain800() {
        //given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
    	player.upgradeOwnFixedAssets(space);
        space = space.upgrade();
        
        //when
        player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10400));
    }

    @Test
    public void shouldSellHouseObtain1200() {
        //given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
    	player.upgradeOwnFixedAssets(space);
        space = space.upgrade();
        
        //when
        player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10600));
    }

    @Test
    public void shouldSellSkyscraperObtain1600() {
        //given
    	player = new Player(1);
    	space = new Space(1);
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
    	player.upgradeOwnFixedAssets(space);
        space = space.upgrade();
    	player.upgradeOwnFixedAssets(space);
        space = space.upgrade();
        
        //when
        player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10800));
    }

    @Test
    public void shouldBuyBlockNeed50Points() {
    	//given
    	player = new Player(1);
    	mine = new Mine(64, 60);
        mine.playerPassOnHere(player, new Game());
    	
    	//when
    	player.buyTool(Block.toolNumber);
        
    	//then
    	assertThat(player.getPoints(), is(10));
    }

    @Test
    public void shouldBuyRobotNeed30Points() {
    	//given
    	player = new Player(1);
    	mine = new Mine(64, 60);
        mine.playerPassOnHere(player, new Game());
    	
        //when
        player.buyTool(Robot.toolNumber);
        
        //then
        assertThat(player.getPoints(), is(30));
    }

    @Test
    public void shouldBuyBombNeed50Points() {
    	//given
    	player = new Player(1);
    	mine = new Mine(64, 60);
        mine.playerPassOnHere(player, new Game());
        
        //when
        player.buyTool(Bomb.toolNumber);
        
        //then
        assertThat(player.getPoints(), is(10));
    }

    @Test
    public void shouldChooseGiftOneFundsAdd2000() {
    	//given
    	player = new Player(1);
    	
    	//when
    	player.chooseGift(1);
        
    	//then
    	assertThat(player.getFunds(), is(12000));
    }

    @Test
    public void shouldChooseGiftTwoPointsAdd200() {
    	//given
    	player = new Player(1);

    	//when
    	player.chooseGift(2);
        
    	//then
    	assertThat(player.getPoints(), is(200));
    }

    @Test
    public void shouldChooseGiftThreeOwnLuck() {
    	//given
    	player = new Player(1);
    	
    	//when
    	player.chooseGift(3);
        
    	//then
    	assertThat(player.isOwnerOfLuck(), is(true));
    }

    @Test
    public void shouldPlayerToBeBombed() {
    	//given
    	player = new Player(1);
    	
    	//when
    	player.toBeBombed();
        
    	//then
    	assertThat(player.isBombed(), is(true));
    }

    @Test
    public void shouldPositionBe14WhenBeBombed() {
    	//given
    	player = new Player(1);
    	
    	//when
    	player.toBeBombed();
        
    	//then
    	assertThat(player.getPosition(), is(14));
    }

    @Test
    public void shouldPause3TimesWhenBeBombed() {
    	//given
    	player = new Player(1);
    	player.toBeBombed();
        
    	//when
    	for (int i = 0; i < 5; i++)
            player.decreaseHospitalizedTimes();
        
    	//then
    	assertThat(player.isBombed(), is(false));
    }

    @Test
    public void shouldPause2TimesWhenBeTrappedInPrison() {
        //given
    	player = new Player(1);
    	player.toBeTrappedInPrison();
    	
    	//when
        for (int i = 0; i < 4; i++)
            player.decreaseTrappedInPrisonTimes();
        
        //then
        assertThat(player.isTrappedInPrison(), is(false));
    }

    @Test
    public void shouldBeFree5TimesWhenOwnOfLuck() {
    	//given
    	player = new Player(1);
    	player.chooseGift(3);
    	
    	//when
        for (int i = 0; i < 7; i++)
            player.decreaseLuckyTimes();

        //then
        assertThat(player.isOwnerOfLuck(), is(false));
    }

    @Test
    public void shouldPlayerBeBankruptWhenFundsBe0() {
    	//given
    	player = new Player(1);
    	
    	//when
    	while (player.getFunds() >= 500) {
            player.handInPassTollToOthers(500);
        }
        player.updateBankruptState(500);
        
        //then
        assertThat(player.isBankrupt(), is(true));
    }

    @Test
    public void shouldPlayerNotBankruptWhenOwnSpace() {
    	//given
    	player = new Player(1);
    	space = new Space(1);
        
    	//when
    	player.buySpace(space.getBuyFunds());
    	while (player.getFunds() >= 500) {
            player.handInPassTollToOthers(500);
        }
        player.updateBankruptState(500);

        //then
        assertThat(player.isBankrupt(), is(false));
    }
}
