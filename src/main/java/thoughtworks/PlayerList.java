package thoughtworks;

import java.io.*;
import java.util.ArrayList;

import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class PlayerList {
	public static final String WHETHER_BUY_SPACE = 
			"是否购买该处空地，";
	public static final String WHETHER_UPGRADE_SPACE = 
			"是否升级该处地产，";
	public static final String END_OF_HINT = 
			"元（Y/N）？";
	
	private ArrayList<Player> playerList =
			new ArrayList<Player>();
	
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
	
	public Player getTheOwnerOfSpace(Space space){
		for(Player player:playerList){
			if(player.isOwnerOfSpace(space))
				return player;
		}
		return null;
	}
	
	public void playerPassOnSpace(Player passer , Space space) 
			throws IOException{
		if(space.isOwned()){
			if(passer.isOwnerOfSpace(space)){
				playerPassOnOwnSpace(passer , space);
			}
			else{
				playerPassOnOtherSpace(passer , space);
			}
		}
		else{
			playerPassOnNewSpace(passer , space);
		}
	}
	
	public char readFromKeyboard() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(
				System.in));
		return (char)(br.read());
	}
	
	public void playerPassOnNewSpace(Player passer , Space space) 
			throws IOException{
//if funds are not enough!!!
		if(passer.getFunds() >= space.getBuyFunds()){
			System.out.println(WHETHER_BUY_SPACE + space.getBuyFunds()
					+ END_OF_HINT);
			if(readFromKeyboard() == 'Y'){
				passer.buySpace(space);
			}
		}
	}

	public void playerPassOnOwnSpace(Player passer , Space space) 
			throws IOException{
//if funds are not enough!!!
		if(passer.getFunds() >= space.getUpgradeFunds()){
			System.out.println(WHETHER_UPGRADE_SPACE + 
					space.getUpgradeFunds() + END_OF_HINT);
			if(readFromKeyboard() == 'Y'){
				passer.upgradeFixedAssets(space.getPositionNumber());
		    }
		}
	}
	
	public void playerPassOnOtherSpace(Player passer , Space space){
//if funds are not enough!!!	
		if(passer.getFunds() >= space.getPassToll()){
			passer.handInPassTollToOthers(space);
			getTheOwnerOfSpace(space).obtainPassTollFromOthers(space);
		}
	}
}
