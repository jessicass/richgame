package thoughtworks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
		mine = new Mine(64);
	}
	
	public String getString(int funds, int points, int[] fixedAssets, int[] tools){
		String fundInfo = "�ʽ�" + funds + "Ԫ��"; 
		String pointInfo = "������" + points + "�㣻";
		String fixedAssetInfo =	"�ز���" + 
		    Space.name + fixedAssets[0] + "����" + 
	        Cottage.name + fixedAssets[1] + "����" + 
	        House.name + fixedAssets[2] + "����" + 
	        Skyscraper.name + fixedAssets[3] + "����";
		String toolInfo = "���ߣ�" + 
	        Block.name + tools[0] + "����" + 
		    Robot.name + tools[1] + "����" + 
		    Bomb.name + tools[2] + "����";
		return fundInfo + "\n" + pointInfo + "\n" + fixedAssetInfo + 
				"\n" + toolInfo + "\n";
	}
	
	@Test
	public void initialPropertyTest(){
		int[] num = {0,0,0,0};
		assertThat(player.queryProperty(), is(getString(10000, 0, num, num)));
	}
	
	@Test
	public void queryPropertyWithOneSpaceTest(){
		mine.playerPassOnHere(player, game);
		player.buySpace(space);
		player.buyTool(Block.toolNumber);
		int[] numOfFixedAssets = {1,0,0,0};
		int[] numOfTools = {1,0,0};
		assertThat(player.queryProperty(), is(getString(9800, 10, numOfFixedAssets, numOfTools)));
	}
	
	@Test
	public void queryPropertyWithOneCottageTest(){
		mine.playerPassOnHere(player, game);
		player.buySpace(space);
		player.upgradeOwnFixedAssets(space.upgrade());
		player.buyTool(Bomb.toolNumber);
		int[] numOfFixedAssets = {0,1,0,0};
		int[] numOfTools = {0,0,1};
		assertThat(player.queryProperty(), is(getString(9600, 10, numOfFixedAssets, numOfTools)));
	}
	
	@Test
	public void queryPropertyWithOneHouseTest(){
		mine.playerPassOnHere(player, game);
		player.buySpace(space);
		player.upgradeOwnFixedAssets(space.upgrade());
		player.upgradeOwnFixedAssets(space.upgrade().upgrade());
		player.buyTool(Robot.toolNumber);
		int[] numOfFixedAssets = {0,0,1,0};
		int[] numOfTools = {0,1,0};
		assertThat(player.queryProperty(), is(getString(9400, 30, numOfFixedAssets, numOfTools)));
	}
	
	@Test
	public void queryPropertyWithOneSkyscraperTest(){
		player.buySpace(space);
		player.upgradeOwnFixedAssets(space.upgrade());
		player.upgradeOwnFixedAssets(space.upgrade().upgrade());
		player.upgradeOwnFixedAssets(space.upgrade().upgrade().upgrade());
		int[] numOfFixedAssets = {0,0,0,1};
		int[] numOfTools = {0,0,0};
		assertThat(player.queryProperty(), is(getString(9200, 0, numOfFixedAssets, numOfTools)));
	}
}
