package thoughtworks;

import java.util.ArrayList;

import thoughtworks.fixedAssets.Space;
import thoughtworks.publicPlace.*;

public class Map {
	public static final int MAX_POSITION = 70;
	private ArrayList<MapObject> map = new ArrayList<MapObject>();
	
	public Map(){
		for(int i = 0;i < MAX_POSITION;i++){
		    switch(i){
		        case StartOfMap.position :
		            map.add(new StartOfMap());
		            break;
		        case Hospital.position :
		            map.add(new Hospital());
		            break;
		        case ToolRoom.position :
		            map.add(new ToolRoom());
		            break;
		        case GiftRoom.position :
		            map.add(new GiftRoom());
		            break;
		        case Prison.position :
		            map.add(new Prison());
		            break;
		        case MagicRoom.position :
		            map.add(new MagicRoom());
		            break;
		        case Mine.position1 :
		            map.add(new Mine(Mine.position1));
		            break;
		        case Mine.position2 :
		            map.add(new Mine(Mine.position2));
		            break;
		        case Mine.position3 :
		            map.add(new Mine(Mine.position3));
		            break;
		        case Mine.position4 :
		            map.add(new Mine(Mine.position4));
		            break;
		        case Mine.position5 :
		            map.add(new Mine(Mine.position5));
		            break;
		        case Mine.position6 :
		            map.add(new Mine(Mine.position6));
		            break;
		        default:
		        	map.add(new Space(i));
		    }
		}
	}
	
	public void drawMap(){
		for(int i = 0; i < 29; i++){
			System.out.print(map.get(i).getSymbol());
		}
		System.out.print("\n");
		String space = "";
		for(int i = 0; i < 27; i++){
			space += " ";
		}
		for(int i = 69 , j = 29; i > 63; i--,j--){
			System.out.println(map.get(i).getSymbol() + space + 
					map.get(j).getSymbol());
		}
		for(int i = 35; i < 64; i++){
			System.out.print(map.get(i).getSymbol());
		}
	}
}
