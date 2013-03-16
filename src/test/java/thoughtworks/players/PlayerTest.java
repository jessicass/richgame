package thoughtworks.players;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.fixedAssets.Space;
import thoughtworks.publicPlace.Mine;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class PlayerTest {
    private Player player;
    private Space space;
    private Mine mine;

    @Before
    public void setUp() {
        player = new Player(1);
        space = new Space(1);
        mine = new Mine(64, 60);
        player.obtainPointsFromMine(mine.getPoints());
    }

    @Test
    public void shouldBuySpaceNeed200() {
        player.buySpace(space.getBuyFunds());
        space.toBeOwned(player);
        assertThat(player.getFunds(), is(9800));
    }

    @Test
    public void shouldUpgradeSpaceToCottageNeed200() {
        shouldBuySpaceNeed200();
        player.upgradeOwnFixedAssets(space);
        assertThat(player.getFunds(), is(9600));
    }

    @Test
    public void shouldUpgradeCottageToHouseNeed200() {
        shouldUpgradeSpaceToCottageNeed200();
        space = (Space) space.upgrade();
        player.upgradeOwnFixedAssets(space);
        assertThat(player.getFunds(), is(9400));

    }

    @Test
    public void shouldUpgradeHouseToSkyscraperNeed200() {
        shouldUpgradeCottageToHouseNeed200();
        space = (Space) space.upgrade();
        player.upgradeOwnFixedAssets(space);
        assertThat(player.getFunds(), is(9200));
    }

    @Test
    public void shouldSellSpaceObtain400() {
        shouldBuySpaceNeed200();
        player.sellSpace(space);
        assertThat(player.getFunds(), is(10200));
    }

    @Test
    public void shouldSellCottageObtain800() {
        shouldUpgradeSpaceToCottageNeed200();
        space = (Space) space.upgrade();
        player.sellSpace(space);
        assertThat(player.getFunds(), is(10400));
    }

    @Test
    public void shouldSellHouseObtain1200() {
        shouldUpgradeCottageToHouseNeed200();
        space = (Space) space.upgrade();
        player.sellSpace(space);
        assertThat(player.getFunds(), is(10600));
    }

    @Test
    public void shouldSellSkyscraperObtain1600() {
        shouldUpgradeHouseToSkyscraperNeed200();
        space = (Space) space.upgrade();
        player.sellSpace(space);
        assertThat(player.getFunds(), is(10800));
    }

    @Test
    public void shouldBuyBlockNeed50Points() {
        player.buyTool(Block.toolNumber);
        assertThat(player.getPoints(), is(10));
    }

    @Test
    public void shouldBuyRobotNeed30Points() {
        player.buyTool(Robot.toolNumber);
        assertThat(player.getPoints(), is(30));
    }

    @Test
    public void shouldBuyBombNeed50Points() {
        player.buyTool(Bomb.toolNumber);
        assertThat(player.getPoints(), is(10));
    }

    @Test
    public void shouldChooseGiftOneFundsAdd2000() {
        player.chooseGift(1);
        assertThat(player.getFunds(), is(12000));
    }

    @Test
    public void shouldChooseGiftTwoPointsAdd200() {
        player.chooseGift(2);
        assertThat(player.getPoints(), is(260));
    }

    @Test
    public void shouldChooseGiftThreeOwnLuck() {
        player.chooseGift(3);
        assertThat(player.isOwnerOfLuck(), is(true));
    }

    @Test
    public void shouldPlayerToBeBombed() {
        player.toBeBombed();
        assertThat(player.isBombed(), is(true));
    }

    @Test
    public void shouldPositionBe14WhenBeBombed() {
        shouldPlayerToBeBombed();
        assertThat(player.getPosition(), is(14));
    }

    @Test
    public void shouldPause3TimesWhenBeBombed() {
        shouldPlayerToBeBombed();
        for (int i = 0; i < 5; i++)
            player.decreaseHospitalizedTimes();
        assertThat(player.isBombed(), is(false));
    }

    @Test
    public void shouldPause2TimesWhenBeTrappedInPrison() {
        player.toBeTrappedInPrison();
        for (int i = 0; i < 4; i++)
            player.decreaseTrappedInPrisonTimes();
        assertThat(player.isTrappedInPrison(), is(false));
    }

    @Test
    public void shouldBeFree5TimesWhenOwnOfLuck() {
        shouldChooseGiftThreeOwnLuck();
        for (int i = 0; i < 7; i++)
            player.decreaseLuckyTimes();
        assertThat(player.isOwnerOfLuck(), is(false));
    }

    @Test
    public void shouldPlayerBeBankruptWhenFundsBe0() {
        while (player.getFunds() >= 500) {
            player.handInPassTollToOthers(500);
        }
        player.testBankrupt(500);
        assertThat(player.isBankrupt(), is(true));
    }

    @Test
    public void shouldPlayerNotBankruptWhenOwnSpace() {
        player.buySpace(space.getBuyFunds());
        while (player.getFunds() >= 500) {
            player.handInPassTollToOthers(500);
        }
        player.testBankrupt(500);
        assertThat(player.isBankrupt(), is(false));
    }
}
