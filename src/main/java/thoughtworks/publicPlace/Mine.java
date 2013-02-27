package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Mine implements MapObject {
	public static final String symbol = "$";
	public static final int position1 = 64;
	public static final int position2 = 65;
	public static final int position3 = 66;
	public static final int position4 = 67;
	public static final int position5 = 68;
	public static final int position6 = 69;
	private int position;
	private int points;
	private boolean hasBlock;
	private boolean hasBomb;
	
	public Mine(int position){
		this.position = position;
		switch(position){
		    case position1:
		    	points = 60;
		    	break;
		    case position2:
		    	points = 80;
		    	break;
		    case position3:
		    	points = 40;
		    	break;
		    case position4:
		    	points = 100;
		    	break;
		    case position5:
		    	points = 80;
		    	break;
		    case position6:
		    	points = 20;
		    	break;
		}
	}
	
	public int getPosition(){
		return position;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String getSymbol(){
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
	}
	
	public MapObject upgrade(){
		return null;
	}
}
