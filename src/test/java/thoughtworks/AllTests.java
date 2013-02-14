package thoughtworks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PlayerFixedAssetsBuildTest.class, PlayerPropertyQueryTest.class,
	PlayerRestOnOtherSpaceTest.class, PlayerBuyToolsInToolRoomTest.class,
	PlayerChooseGiftInPresentRoomTest.class, CreatPlayerListTest.class})
public class AllTests {

}
