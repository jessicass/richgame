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
    public static final int HosipitalPosition = 14;
    private ArrayList<MapObject> mapList = new ArrayList<MapObject>();
    private static final Console console = Enigma.getConsole("");

    public Map() {
        mapList.add(new StartOfMap(0));
        initialSpace(mapList, 1, 13);
        mapList.add(new Hospital(14));
        initialSpace(mapList, 15, 13);
        mapList.add(new ToolRoom(28));
        initialSpace(mapList, 29, 6);
        mapList.add(new GiftRoom(35));
        initialSpace(mapList, 36, 13);
        mapList.add(new Prison(49));
        initialSpace(mapList, 50, 13);
        mapList.add(new MagicRoom(63));
        mapList.add(new Mine(64, 60));
        mapList.add(new Mine(65, 80));
        mapList.add(new Mine(66, 40));
        mapList.add(new Mine(67, 100));
        mapList.add(new Mine(68, 80));
        mapList.add(new Mine(69, 20));
    }

    private void initialSpace(ArrayList<MapObject> mapList, int startNum, int length) {
        for (int i = startNum; i < startNum + length; i++) {
            mapList.add(new Space(i));
        }
    }

    public void drawMap(ArrayList<Player> players) {
        String middleSpace = "";
        for (int i = 0; i < 27; i++) {
            middleSpace += " ";
        }

        System.out.print("\n");
        for (int i = 0; i < 29; i++) {
            setForegroundColor(i, console);
            System.out.print(mapList.get(i).getSymbol(players));
            console.setTextAttributes(new TextAttributes(Color.WHITE));
        }

        System.out.print("\n");
        for (int i = MAX_POSITION, j = 29; i > 63; i--, j++) {
            System.out.print(mapList.get(i).getSymbol(players) + middleSpace);
            setForegroundColor(j, console);
            System.out.print(mapList.get(j).getSymbol(players));
            console.setTextAttributes(new TextAttributes(Color.WHITE));
            System.out.print("\n");
        }

        for (int i = 63; i > 34; i--) {
            setForegroundColor(i, console);
            System.out.print(mapList.get(i).getSymbol(players));
            console.setTextAttributes(new TextAttributes(Color.WHITE));
        }
        System.out.print("\n");
        console.setTextAttributes(new TextAttributes(Color.WHITE));
    }

    public void setForegroundColor(int position, Console console) {
        if (!isSpaceWithPositionOf(position)) {
            return;
        }
        Space space = (Space) getMapObjectWithIndex(position);
        if (space.isOwned()) {
            console.setTextAttributes(new TextAttributes(space.getOwner().getColor()));
        }
    }

    public MapObject getMapObjectWithIndex(int index) {
        return mapList.get(index);
    }

    public void upgradeFixedAssets(Space space) {
        mapList.set(space.getPosition(), space);
    }

    public boolean isSpaceWithPositionOf(int position) {
        return mapList.get(position) instanceof Space;
    }
}
