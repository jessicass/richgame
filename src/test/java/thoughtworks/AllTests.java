package thoughtworks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import thoughtworks.functionalTest.CreatPlayerListTest;
import thoughtworks.functionalTest.MapDrawTest;
import thoughtworks.functionalTest.PlayerBuyToolsInToolRoomTest;
import thoughtworks.functionalTest.PlayerChooseGiftInPresentRoomTest;
import thoughtworks.functionalTest.PlayerFixedAssetsBuildTest;
import thoughtworks.functionalTest.PlayerPassOnOtherSpaceTest;
import thoughtworks.functionalTest.PlayerPropertyQueryTest;
import thoughtworks.functionalTest.PlayerSellToolsTest;
import thoughtworks.functionalTest.UpdatePlayerPositionTest;
import thoughtworks.players.FixedAssetsOfPlayerTest;
import thoughtworks.players.SystemPlayerTest;
import thoughtworks.players.PlayerTest;
import thoughtworks.players.ToolsOfPlayerTest;

@RunWith(Suite.class)
@SuiteClasses({ PlayerFixedAssetsBuildTest.class, PlayerPropertyQueryTest.class,
	PlayerPassOnOtherSpaceTest.class, PlayerBuyToolsInToolRoomTest.class,
	PlayerChooseGiftInPresentRoomTest.class, CreatPlayerListTest.class,
	MapDrawTest.class, UpdatePlayerPositionTest.class,
	PlayerSellToolsTest.class,FixedAssetsOfPlayerTest.class,
	SystemPlayerTest.class,PlayerTest.class,ToolsOfPlayerTest.class})
public class AllTests {

}
