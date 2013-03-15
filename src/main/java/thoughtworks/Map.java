package thoughtworks;

import java.util.ArrayList;

import java.awt.Color;
import enigma.console.*;
import enigma.core.*;

import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;
import thoughtworks.publicPlace.*;

public class Map {
	public static final int MAX_POSITION = 69;
	private ArrayList<MapObject> mapList = new ArrayList<MapObject>();
	private static final Console console = Enigma.getConsole("");

	public Map() {
		for (int i = 0; i <= MAX_POSITION; i++) {
			switch (i) {
			case StartOfMap.position:
				mapList.add(new StartOfMap());
				break;
			case Hospital.position:
				mapList.add(new Hospital());
				break;
			case ToolRoom.position:
				mapList.add(new ToolRoom());
				break;
			case GiftRoom.position:
				mapList.add(new GiftRoom());
				break;
			case Prison.position:
				mapList.add(new Prison());
				break;
			case MagicRoom.position:
				mapList.add(new MagicRoom());
				break;
			case Mine.position1:
				mapList.add(new Mine(Mine.position1));
				break;
			case Mine.position2:
				mapList.add(new Mine(Mine.position2));
				break;
			case Mine.position3:
				mapList.add(new Mine(Mine.position3));
				break;
			case Mine.position4:
				mapList.add(new Mine(Mine.position4));
				break;
			case Mine.position5:
				mapList.add(new Mine(Mine.position5));
				break;
			case Mine.position6:
				mapList.add(new Mine(Mine.position6));
				break;
			default:
				mapList.add(new Space(i));
			}
		}
	}

	public void drawMap(ArrayList<Player> players) {	     
		String middleSpace = "";
		for (int i = 0; i < 27; i++) {
			middleSpace += " ";
		}

		System.out.print("\n");
		for (int i = 0; i < 29; i++) {
			setForegroundColor(i, players, console);
			System.out.print(mapList.get(i).getSymbol(players));
			console.setTextAttributes(new TextAttributes(Color.WHITE));
		}

		System.out.print("\n");
		for (int i = MAX_POSITION, j = 29; i > 63; i--, j++) {
			System.out.print(mapList.get(i).getSymbol(players) + middleSpace);
			setForegroundColor(j, players, console);
			System.out.print(mapList.get(j).getSymbol(players));
			console.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("\n");
		}

		for (int i = 63; i > 34; i--) {
			setForegroundColor(i, players, console);
			System.out.print(mapList.get(i).getSymbol(players));
			console.setTextAttributes(new TextAttributes(Color.WHITE));
		}
		System.out.print("\n");
		console.setTextAttributes(new TextAttributes(Color.WHITE));
	}
	
	public void setForegroundColor(int position, ArrayList<Player> players, Console console){
		if(!isSpaceWithPositionOf(position)){
			return;
		}
		for(Player player: players){
			Space space = (Space)getMapObjectWithIndex(position);
			if(space.isOwnedBy(player)){
				 console.setTextAttributes(new TextAttributes(player.getColor()));
				 return;
			}
		}
	}

	public MapObject getMapObjectWithIndex(int index) {
		return mapList.get(index);
	}
	
	public void upgradeFixedAssets(Space space) {
		mapList.set(space.getPosition(), space);
	}
	
	public boolean isSpaceWithPositionOf(int position){
		if((position != StartOfMap.position) && 
		  (position != Hospital.position) && 
		  (position != ToolRoom.position) &&
		  (position != GiftRoom.position) &&
		  (position != Prison.position) &&
		  (position != MagicRoom.position) &&
		  (position != Mine.position1) &&
		  (position != Mine.position2) &&
		  (position != Mine.position3) &&
		  (position != Mine.position4) &&
		  (position != Mine.position5) &&
		  (position != Mine.position6) ){
			  return true;
		  }
		return false;
	}
}
