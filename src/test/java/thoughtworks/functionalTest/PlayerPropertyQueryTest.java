package thoughtworks.functionalTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import thoughtworks.Game;
import thoughtworks.command.Query;
import thoughtworks.fixedAssets.Cottage;
import thoughtworks.fixedAssets.House;
import thoughtworks.fixedAssets.Skyscraper;
import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;
import thoughtworks.publicPlace.Mine;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;
import thoughtworks.tools.Robot;

public class PlayerPropertyQueryTest {
	private Player player;
	private Space space;
	private Mine mine;
	private Game game = new Game();
	
	@Before
	public void setUp(){
		player = new Player(1);
		space = new Space(1);
		mine = new Mine(64, 60);
	}
	
	public String getString(int funds, int points, int[] fixedAssets, int[] tools){
		String fundInfo = "资金：" + funds + "元；"; 
		String pointInfo = "点数：" + points + "点；";
		String fixedAssetInfo =	"地产：" + 
		    Space.name + fixedAssets[0] + "处；" + 
	        Cottage.name + fixedAssets[1] + "处；" + 
	        House.name + fixedAssets[2] + "处；" + 
	        Skyscraper.name + fixedAssets[3] + "处；";
		String toolInfo = "道具：" + 
	        Block.name + tools[0] + "个；" + 
		    Robot.name + tools[1] + "个；" + 
		    Bomb.name + tools[2] + "个；";
		return fundInfo + "\n" + pointInfo + "\n" + fixedAssetInfo + 
				"\n" + toolInfo + "\n";
	}
	
	@Test
	public void initialPropertyTest(){
		int[] num = {0,0,0,0};
		assertThat(Query.queryProperty(player), is(getString(10000, 0, num, num)));
	}
	
	@Test
	public void queryPropertyWithOneSpaceTest(){
		mine.playerPassOnHere(player, game);
		player.buySpace(space.getBuyFunds());
		space.toBeOwned(player);
		player.buyTool(Block.toolNumber);
		int[] numOfFixedAssets = {1,0,0,0};
		int[] numOfTools = {1,0,0};
		assertThat(Query.queryProperty(player), is(getString(9800, 10, numOfFixedAssets, numOfTools)));
	}
	
	@Test
	public void queryPropertyWithOneCottageTest(){
		mine.playerPassOnHere(player, game);
		player.buySpace(space.getBuyFunds());
		space.toBeOwned(player);
		player.upgradeOwnFixedAssets(space);
		player.buyTool(Bomb.toolNumber);
		int[] numOfFixedAssets = {0,1,0,0};
		int[] numOfTools = {0,0,1};
		assertThat(Query.queryProperty(player), is(getString(9600, 10, numOfFixedAssets, numOfTools)));
	}
	
	@Test
	public void queryPropertyWithOneHouseTest(){
		mine.playerPassOnHere(player, game);
		player.buySpace(space.getBuyFunds());
		space.toBeOwned(player);
		player.upgradeOwnFixedAssets(space);
		player.upgradeOwnFixedAssets((Space)space.upgrade());
		player.buyTool(Robot.toolNumber);
		int[] numOfFixedAssets = {0,0,1,0};
		int[] numOfTools = {0,1,0};
		assertThat(Query.queryProperty(player), is(getString(9400, 30, numOfFixedAssets, numOfTools)));
	}
	
	@Test
	public void queryPropertyWithOneSkyscraperTest(){
		player.buySpace(space.getBuyFunds());
		space.toBeOwned(player);
		player.upgradeOwnFixedAssets(space);
		player.upgradeOwnFixedAssets((Space)space.upgrade());
		player.upgradeOwnFixedAssets((Space)space.upgrade().upgrade());
		int[] numOfFixedAssets = {0,0,0,1};
		int[] numOfTools = {0,0,0};
		assertThat(Query.queryProperty(player), is(getString(9200, 0, numOfFixedAssets, numOfTools)));
	}
}
