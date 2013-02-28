package thoughtworks.publicPlace;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.command.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class GiftRoom implements MapObject {
	public static final String symbol = "G";
	public static final int position = 35;
	public static final String WELCOME = "��ӭ������Ʒ�ݣ�������" +
			"������Ҫ����Ʒ�ı�ţ�";
	public static final int presentFunds = 2000;
	public static final int presentPoints = 200;
	public static final int MAX_GIFT_NUMBER = 3;
	public static final int MIN_GIFT_NUMBER = 1;
	private boolean hasBlock;
	private boolean hasBomb;
	
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
	
	public int getPosition(){
		return position;
	}
	
	public static String giftInfoShow() {
		String info = "��Ʒ" + "\t" + "���" + "\n";
		info += "����" + "\t" + "1" + "\n";
		info += "������" + "\t" + "2" + "\n";
		info += "����" + "\t" + "3" + "\n";
		return info;	
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
		System.out.println(giftInfoShow());
		System.out.println(WELCOME);
		while(true){
			int input = Input.getInteger();
			if(Input.isIntegerInArea(input, MIN_GIFT_NUMBER, 
					MAX_GIFT_NUMBER)){
				passer.chooseGift(input);
				System.out.println("��ϲ�������Ʒ��");
				return;
			}
		}
	}
	
	public MapObject upgrade(){
		return null;
	}
}
