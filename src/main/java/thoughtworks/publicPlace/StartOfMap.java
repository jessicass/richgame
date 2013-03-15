package thoughtworks.publicPlace;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class StartOfMap implements MapObject {
	public static final String symbol = "S";
	public static final int position = 0;
	private boolean hasBlock;
	private boolean hasBomb;
	
	public String getSymbol(ArrayList<Player> players){
		for(Player player: players){
			if(player.getPosition() == position){
				return player.getShortName();
			}
		}
		if(hasBlock){
			return Block.symbol;
		}
		if(hasBomb){
			return Bomb.symbol;
		}
		return symbol;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setBlock(){
		hasBlock = true;
	}
	
	public void resetBlock(){
		hasBlock = false;
	}
	
	public boolean hasBlock(){
		return hasBlock;
	}
	
	public void setBomb(){
		hasBomb = true;
	}
	
	public void resetBomb(){
		hasBomb = false;
	}
	
	public boolean hasBomb(){
		return hasBomb;
	}

	public void playerPassOnHere(Player player, Game game) {}
}
