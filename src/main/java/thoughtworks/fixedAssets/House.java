package thoughtworks.fixedAssets;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thoughtworks.MapObject;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class House extends Space implements MapObject {
	public final String symbol = "2";
	public static final String name = "洋房";
	public final int level = 2;
	private int upgradeFunds;
	private int passToll;
	
	public House(int position){
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
		Skyscraper skyscraper = new Skyscraper(position);
		skyscraper.totalCost = totalCost + upgradeFunds;
		skyscraper.isOwned = isOwned;
		skyscraper.hasBlock = hasBlock;
		skyscraper.hasBomb = hasBomb;
		return skyscraper;
	}
	
	public Space sell(){
		return new Space(position);
	}
}
