package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.players.Player;
import thoughtworks.publicPlace.*;
import thoughtworks.tools.*;

public class PlayerBuyToolsInToolRoomTest {
	private Player player;
	private Mine mine;
	
	@Before
	public void setUp(){
		player = new Player(1);
		mine = new Mine(64);
		player.obtainPointsFromMine(mine);
	}
	
	@Test
	public void shouldBuyBlockNeed50Points(){
		player.buyTool(Block.toolNumber);
		assertThat(player.getPoints(), is(10));
	}
	
	@Test
	public void shouldBuyRobotNeed30Points(){
		player.buyTool(Robot.toolNumber);
		assertThat(player.getPoints(), is(30));
	}
	
	@Test
	public void shouldBuyBombNeed50Points(){
		player.buyTool(Bomb.toolNumber);
		assertThat(player.getPoints(), is(10));
	}
	
	@Test
	public void shouldPointsOfPlayerNotEnoughForBomb(){
		player.buyTool(Bomb.toolNumber);
		assertThat(ToolRoom.playerBuyTool(player, Bomb.toolNumber), 
				is("您当前剩余的点数为" + player.getPoints() + 
					"，不足以购买" + Bomb.toolNumber + "道具"));
	}
	
	@Test
	public void shouldPointsOfPlayerNotEnoughForAllTools(){
		player.buyTool(Bomb.toolNumber);
		assertThat(ToolRoom.isPointsOfPlayerEnough(player), is(false));
	}
	
	@Test
	public void shouldTotalNumberOfToolsBeyondLimit(){
		for(int i = 0; i < 10; i++){
			player.buyTool(Bomb.toolNumber);
			player.obtainPointsFromMine(mine);
		}
		assertThat(ToolRoom.playerBuyTool(player, Bomb.toolNumber), 
				is(ToolRoom.NUMBER_OF_TOOLS_BEYOND_LIMIT));
	}
}
