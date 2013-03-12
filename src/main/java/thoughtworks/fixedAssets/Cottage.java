package thoughtworks.fixedAssets;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class Cottage extends Space implements MapObject {
	public final String symbol = "1";
	public static final String name = "茅屋";
	public final int level = 1;
	private int upgradeFunds;
	private int passToll;
	
	public Cottage(int position){
		super(position);
		this.upgradeFunds = super.getUpgradeFunds();
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
	
	public int getUpgradeFunds(){
		return this.upgradeFunds;
	}
	
	public int getPassToll(){
		return this.passToll;
	}
	
	public MapObject upgrade(){
		House house = new House(position);
		house.totalCost = totalCost + upgradeFunds;
		house.isOwned = isOwned;
		house.hasBlock = hasBlock;
		house.hasBomb = hasBomb;
		return house;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
