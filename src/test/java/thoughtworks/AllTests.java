package thoughtworks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import thoughtworks.functionClass.PositionUpdateTest;
import thoughtworks.functionClass.RoleNumberTransferTest;
import thoughtworks.functionalTest.CreatPlayerListTest;
import thoughtworks.functionalTest.MapDrawTest;
import thoughtworks.functionalTest.PlayerBuyToolsInToolRoomTest;
import thoughtworks.functionalTest.PlayerChooseGiftInPresentRoomTest;
import thoughtworks.functionalTest.PlayerFixedAssetsBuildTest;
import thoughtworks.functionalTest.PlayerPassOnOtherSpaceTest;
import thoughtworks.functionalTest.PlayerPropertyQueryTest;
import thoughtworks.functionalTest.PlayerSellToolsTest;
import thoughtworks.functionalTest.UpdatePlayerPositionTest;
import thoughtworks.players.FixedAssetsTest;
import thoughtworks.players.SystemPlayerTest;
import thoughtworks.players.PlayerTest;
import thoughtworks.players.ToolsTest;

@RunWith(Suite.class)
@SuiteClasses({ PlayerFixedAssetsBuildTest.class, PlayerPropertyQueryTest.class,
	PlayerPassOnOtherSpaceTest.class, PlayerBuyToolsInToolRoomTest.class,
	PlayerChooseGiftInPresentRoomTest.class, CreatPlayerListTest.class,
	MapDrawTest.class, UpdatePlayerPositionTest.class,
	PlayerSellToolsTest.class,FixedAssetsTest.class,
	SystemPlayerTest.class,PlayerTest.class,ToolsTest.class,
	RoleNumberTransferTest.class,PositionUpdateTest.class})
public class AllTests {

}
