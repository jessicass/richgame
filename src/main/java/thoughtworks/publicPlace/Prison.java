package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.players.Player;

public class Prison extends Terrain implements MapObject {
	public static final String symbol = "P";
		
	public Prison(int position) {
		super(position);
	}

	@Override
	public String getSymbol(){
		return symbol;
	}
	
	@Override
	public void playerPassOnHere(Player passer, Game game) {
		passer.toBeTrappedInPrison();
		System.out.println("非常抱歉，您被困在监狱了");
	}
}
