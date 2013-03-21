package thoughtworks.players;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.fixedAssets.Space;
import thoughtworks.publicPlace.Mine;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;
import thoughtworks.tools.Tool;

public class PlayerTest {
    private Player player = new Player(1);
    private Space space = new Space(1);
    private Mine mine = new Mine(64, 60);;

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

    private void playerOwnASpace() {
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);       
	}
    
    @Test
    public void shouldUpgradeSpaceToCottageNeed200() {
    	//given
    	playerOwnASpace();
        
    	//when
    	player.upgradeOwnFixedAssets(space);
        
        //then
        assertThat(player.getFunds(), is(9600));
    }

    @Test
    public void shouldUpgradeCottageToHouseNeed200() {
        //given
        playerOwnACottage();
        
    	//when
    	player.upgradeOwnFixedAssets(space);
        
        //then
        assertThat(player.getFunds(), is(9400));
    }

	private void playerOwnACottage() {
		playerOwnASpace();
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
	}

    @Test
    public void shouldUpgradeHouseToSkyscraperNeed200() {
        //given
        playerOwnAHouse();
    	
    	//when
    	player.upgradeOwnFixedAssets(space);
        
        //then
        assertThat(player.getFunds(), is(9200));
    }

	private void playerOwnAHouse() {
		playerOwnACottage();
    	player.upgradeOwnFixedAssets(space);
    	space = space.upgrade();
	}

    @Test
    public void shouldSellSpaceObtain400() {
    	//given
    	playerOwnASpace();
        
    	//when
    	player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10200));
    }

    @Test
    public void shouldSellCottageObtain800() {
        //given
        playerOwnACottage();
        
        //when
        player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10400));
    }

    @Test
    public void shouldSellHouseObtain1200() {
        //given
        playerOwnAHouse();
        
        //when
        player.sellSpace(space);
        
        //then
        assertThat(player.getFunds(), is(10600));
    }

    @Test
    public void shouldSellSkyscraperObtain1600() {
        //given
        playerOwnAHouse();
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
        mine.playerPassOnHere(player, new Game());
    	
    	//when
    	player.buyTool(Tool.createTool(Block.toolNumber));
        
    	//then
    	assertThat(player.getPoints(), is(10));
    }

    @Test
    public void shouldBuyRobotNeed30Points() throws IOException {
    	//given
        mine.playerPassOnHere(player, new Game());
    	
        //when
        player.buyTool(Tool.createTool(Robot.toolNumber));
        
        //then
        assertThat(player.getPoints(), is(30));
    }

    @Test
    public void shouldBuyBombNeed50Points() {
    	//given
        mine.playerPassOnHere(player, new Game());
        
        //when
        player.buyTool(Tool.createTool(Bomb.toolNumber));
        
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
    	playerOwnASpace();
        
    	//when
    	while (player.getFunds() >= 500) {
            player.handInPassTollToOthers(500);
        }
        player.updateBankruptState(500);

        //then
        assertThat(player.isBankrupt(), is(false));
    }
}
