package thoughtworks.publicPlace;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Mine implements MapObject {
	public static final String symbol = "$";
	private int position;
	private int points;
	private boolean hasBlock;
	private boolean hasBomb;
	
	public Mine(int position, int points) {
		this.position = position;
		this.points = points;
	}

	public int getPosition(){
		return position;
	}
	
	public int getPoints(){
		return points;
	}
	
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

	public void playerPassOnHere(Player passer, Game game) {
		passer.obtainPointsFromMine(points);
		System.out.println("恭喜您在矿地获得" + points + "点数");
	}
}
