package thoughtworks.players;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ToolsTest {
	private Tools toolsOfPlayer = new Tools();
	
	@Test
	public void shouldAfterBuyBlockNumberOfBlockBe1(){
		toolsOfPlayer.buyTool(1);
		assertThat(toolsOfPlayer.getNumberOfTools(1), is(1));
	}
	
	@Test
	public void shouldAfterBuyRobotNumberOfRobotBe1(){
		toolsOfPlayer.buyTool(2);
		assertThat(toolsOfPlayer.getNumberOfTools(2), is(1));
	}
	
	@Test
	public void shouldAfterBuyBombNumberOfBombBe1(){
		toolsOfPlayer.buyTool(3);
		assertThat(toolsOfPlayer.getNumberOfTools(3), is(1));
	}
	
	@Test
	public void shouldAfterDecreaseBlockNumberOfBlockBe0(){
		shouldAfterBuyBlockNumberOfBlockBe1();
		toolsOfPlayer.decreaseNumberOfTools(1);
		assertThat(toolsOfPlayer.getNumberOfTools(1), is(0));
	}

	@Test
	public void shouldAfterDecreaseRobotNumberOfRobotBe0(){
		shouldAfterBuyRobotNumberOfRobotBe1();
		toolsOfPlayer.decreaseNumberOfTools(2);
		assertThat(toolsOfPlayer.getNumberOfTools(2), is(0));
	}
	
	@Test
	public void shouldAfterDecreaseBombNumberOfBombBe0(){
		shouldAfterBuyBombNumberOfBombBe1();
		toolsOfPlayer.decreaseNumberOfTools(3);
		assertThat(toolsOfPlayer.getNumberOfTools(3), is(0));
	}
	
	@Test
	public void shouldBeforeBuyBlockNotOwnBlock(){
		assertThat(toolsOfPlayer.isOwnToolWithNumberOf(1), is(false));
	}
	
	@Test
	public void shouldAfterBuyBlockIsOwnBlock(){
		shouldAfterBuyBlockNumberOfBlockBe1();
		assertThat(toolsOfPlayer.isOwnToolWithNumberOf(1), is(true));
	}
	
	@Test
	public void shouldBeforeBuyRobotNotOwnRobot(){
		assertThat(toolsOfPlayer.isOwnToolWithNumberOf(2), is(false));
	}
	
	@Test
	public void shouldAfterBuyRobotIsOwnRobot(){
		shouldAfterBuyRobotNumberOfRobotBe1();
		assertThat(toolsOfPlayer.isOwnToolWithNumberOf(2), is(true));
	}
	
	@Test
	public void shouldBeforeBuyBombNotOwnBomb(){
		assertThat(toolsOfPlayer.isOwnToolWithNumberOf(3), is(false));
	}
	
	@Test
	public void shouldAfterBuyBombIsOwnBomb(){
		shouldAfterBuyBombNumberOfBombBe1();
		assertThat(toolsOfPlayer.isOwnToolWithNumberOf(3), is(true));
	}
	
	@Test
	public void shouldBuyBlockNeed50Points(){
		assertThat(toolsOfPlayer.getPointsOfToolWithNumberOf(1), is(50));
	}
	
	@Test
	public void shouldBuyRobotNeed30Points(){
		assertThat(toolsOfPlayer.getPointsOfToolWithNumberOf(2), is(30));
	}
	
	@Test
	public void shouldBuyBombNeed50Points(){
		assertThat(toolsOfPlayer.getPointsOfToolWithNumberOf(3), is(50));
	}
}
