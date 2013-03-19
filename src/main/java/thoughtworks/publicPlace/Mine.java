package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;

public class Mine extends Terrain implements MapObject {
	public static final String symbol = "$";
	private int points;
	
	public Mine(int position, int points) {
		super(position);
		this.points = points;
	}

	@Override
	public String getSymbol(){
		return symbol;
	}
	
	@Override
	public void playerPassOnHere(Player passer, Game game) {
		passer.obtainPointsFromMine(points);
		System.out.println("恭喜您在矿地获得" + points + "点数");
	}
}
