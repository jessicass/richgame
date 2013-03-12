package thoughtworks.fixedAssets;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Skyscraper extends Space implements MapObject {
	public final String symbol = "3";
	public static final String name = "摩天楼";
	public final int level = 3;
	private int passToll;
	
	public Skyscraper(int position){
		super(position);
		this.passToll = super.getPassToll() * Integer.parseInt(
				new DecimalFormat("0").format(Math.pow(2, level)));
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
	
	public int getLevel(){
		return level;
	}
	
	public int getPassToll(){
		return this.passToll;
	}
	
	public MapObject upgrade(){
		return null;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
