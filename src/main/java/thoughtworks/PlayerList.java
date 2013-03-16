package thoughtworks;

import java.util.ArrayList;

import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class PlayerList {	
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public PlayerList(int[] roleNumbers){
		for(int i = 0; i < roleNumbers.length; i++){
			playerList.add(new Player(roleNumbers[i]));
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return playerList;
	}
	
	public Player getPlayer(int index){
		return playerList.get(index-1);
	}

}
