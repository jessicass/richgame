package thoughtworks;

import java.util.ArrayList;

import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;
import thoughtworks.publicPlace.*;

public class Map {
	public static final int MAX_POSITION = 69;
	private ArrayList<MapObject> mapList = new ArrayList<MapObject>();

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

	public void drawMapBeforePlay() {
		String middleSpace = "";
		for (int i = 0; i < 27; i++) {
			middleSpace += " ";
		}

		System.out.print("\n");
		for (int i = 0; i < 29; i++) {
			System.out.print(mapList.get(i).getSymbol());
		}

		System.out.print("\n");
		for (int i = MAX_POSITION, j = 29; i > 63; i--, j++) {
			System.out.println(mapList.get(i).getSymbol() + middleSpace
					+ mapList.get(j).getSymbol());
		}

		for (int i = 35; i < 64; i++) {
			System.out.print(mapList.get(i).getSymbol());
		}
	}

	public void drawMap(ArrayList<Player> players) {
		String middleSpace = "";
		for (int i = 0; i < 27; i++) {
			middleSpace += " ";
		}

		System.out.print("\n");
		for (int i = 0; i < 29; i++) {
			boolean isSymbolDrawed = drawPlayerInMap(players, i);
			if (!isSymbolDrawed) {
				System.out.print(mapList.get(i).getSymbol());
			}
		}

		System.out.print("\n");
		for (int i = 69, j = 29; i > 63; i--, j++) {
			boolean isSymbolDrawed = drawPlayerInMap(players, i, j, middleSpace);
			if (!isSymbolDrawed) {
				System.out.println(mapList.get(i).getSymbol() + middleSpace
						+ mapList.get(j).getSymbol());
			}
		}

		for (int i = 35; i < 64; i++) {
			boolean isSymbolDrawed = drawPlayerInMap(players, i);
			if (!isSymbolDrawed) {
				System.out.print(mapList.get(i).getSymbol());
			}
		}
	}

	public boolean drawPlayerInMap(ArrayList<Player> players, int i) {
		boolean isSymbolDrawed = false;
		for (Player player : players) {
			if (i == player.getPosition()) {
				System.out.print(player.getShortName());
				isSymbolDrawed = true;
				break;
			}
		}
		return isSymbolDrawed;
	}

	public boolean drawPlayerInMap(ArrayList<Player> players, int i, int j,
			String middleSpace) {
		boolean isSymbolDrawed = false;
		for (Player player : players) {
			if (i == player.getPosition()) {
				System.out.println(player.getShortName() + middleSpace
						+ mapList.get(j).getSymbol());
				isSymbolDrawed = true;
				break;
			}
			if (j == player.getPosition()) {
				System.out.println(mapList.get(i).getSymbol() + middleSpace
						+ player.getShortName());
				isSymbolDrawed = true;
				break;
			}
		}
		return isSymbolDrawed;
	}

	public MapObject getMapObjectWithIndex(int index) {
		return mapList.get(index);
	}
	
	public void upgradeFixedAssets(MapObject mapObject) {
		mapList.set(mapObject.getPosition(), mapObject);
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
