package thoughtworks;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class MapDrawTest {
	private Game game = new Game();
	private ArrayList<Player> players;
	private Map map;
	
	@Before
	public void setUp(){
		game.isCreatPlayerListSuccess(1234);
		players = game.getPlayers();
		map = game.getMap();
	}
	
	 @Test
	public void mapDrawBeforePlayTest(){
		map.drawMap(players);
	}
	
	@Test
	public void mapDrawWithPlayersAtStartTest(){
        map.drawMap(players);
	}
	
	@Test
	public void mapDrawWithPlayersInDifferentPositionTest(){
		players.get(0).updatePosition(3);
		players.get(1).updatePosition(25);
		players.get(2).updatePosition(32);
		players.get(3).updatePosition(65);
        map.drawMap(players);
	}

	@Test
	public void mapDrawWithSpaceBeUpdatedTest(){
		players.get(0).updatePosition(1);
		players.get(0).buySpace((Space)map.getMapObjectWithIndex(3));
		game.upgradeFixedAssetsWithPositionOf(3);
		map.drawMap(players);
	}
}
