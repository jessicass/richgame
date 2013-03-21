package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.players.Player;
import thoughtworks.publicPlace.*;
import thoughtworks.tools.*;

public class PlayerBuyToolsInToolRoomTest {
	private Player player;
	private Mine mine;
	
	@Before
	public void setUp(){
		player = new Player(1);
		mine = new Mine(64, 60);
		mine.playerPassOnHere(player, new Game());
	}
	
	@Test
	public void shouldBuyBlockNeed50Points(){
		player.buyTool(Tool.createTool(Block.toolNumber));
		assertThat(player.getPoints(), is(10));
	}
	
	@Test
	public void shouldBuyRobotNeed30Points(){
		player.buyTool(Tool.createTool(Robot.toolNumber));
		assertThat(player.getPoints(), is(30));
	}
	
	@Test
	public void shouldBuyBombNeed50Points(){
		player.buyTool(Tool.createTool(Bomb.toolNumber));
		assertThat(player.getPoints(), is(10));
	}
	
	@Test
	public void shouldPointsOfPlayerNotEnoughForBomb(){
		player.buyTool(Tool.createTool(Bomb.toolNumber));
		assertThat(ToolRoom.isPointsEnoughToBuyToolWithNumber(
				player.getPoints(), Tool.createTool(Bomb.toolNumber)), is(false));
	}
	
	@Test
	public void shouldPointsOfPlayerNotEnoughForAllTools(){
		player.buyTool(Tool.createTool(Bomb.toolNumber));
		assertThat(ToolRoom.isPointsEnoughToBuyAllTool(
				player.getPoints()), is(false));
	}
	
	@Test
	public void shouldTotalNumberOfToolsBeyondLimit(){
		for(int i = 0; i < 10; i++){
			player.buyTool(Tool.createTool(Bomb.toolNumber));
			mine.playerPassOnHere(player, new Game());
		}
		assertThat(ToolRoom.isNumberOfTotalToolsNotBeyondLimits(
				player.getTools().getTotalNumberOfTools()), is(false));
	}
}
