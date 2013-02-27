package thoughtworks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PlayerFixedAssetsBuildTest.class, PlayerPropertyQueryTest.class,
	PlayerPassOnOtherSpaceTest.class, PlayerBuyToolsInToolRoomTest.class,
	PlayerChooseGiftInPresentRoomTest.class, CreatPlayerListTest.class,
	MapDrawTest.class, UpdatePlayerPositionTest.class,
	PlayerSellToolsTest.class})
public class AllTests {

}
