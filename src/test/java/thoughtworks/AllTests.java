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
import thoughtworks.playersTest.FixedAssetsOfPlayerTest;
import thoughtworks.playersTest.PlayerNameTest;
import thoughtworks.playersTest.PlayerTest;
import thoughtworks.playersTest.ToolsOfPlayerTest;

@RunWith(Suite.class)
@SuiteClasses({ PlayerFixedAssetsBuildTest.class, PlayerPropertyQueryTest.class,
	PlayerPassOnOtherSpaceTest.class, PlayerBuyToolsInToolRoomTest.class,
	PlayerChooseGiftInPresentRoomTest.class, CreatPlayerListTest.class,
	MapDrawTest.class, UpdatePlayerPositionTest.class,
	PlayerSellToolsTest.class,FixedAssetsOfPlayerTest.class,
	PlayerNameTest.class,PlayerTest.class,ToolsOfPlayerTest.class})
public class AllTests {

}
