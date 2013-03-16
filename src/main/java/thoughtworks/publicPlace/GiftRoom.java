package thoughtworks.publicPlace;

import java.util.ArrayList;

import thoughtworks.Game;
import thoughtworks.MapObject;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;
import thoughtworks.tools.Block;
import thoughtworks.tools.Bomb;

public class GiftRoom implements MapObject {
	public static final String symbol = "G";
	private int position;
	public static final String WELCOME = "欢迎光临礼品屋，请输入" +
			"您所需要的礼品的编号：";
	public static final int presentFunds = 2000;
	public static final int presentPoints = 200;
	public static final int MAX_GIFT_NUMBER = 3;
	public static final int MIN_GIFT_NUMBER = 1;
	private boolean hasBlock;
	private boolean hasBomb;
	
	public GiftRoom(int position) {
		this.position = position;
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
	
	public int getPosition(){
		return position;
	}
	
	public static String giftInfoShow() {
		String info = "礼品" + "\t" + "编号" + "\n";
		info += "奖金" + "\t" + "1" + "\n";
		info += "点数卡" + "\t" + "2" + "\n";
		info += "福神" + "\t" + "3" + "\n";
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
				System.out.println("恭喜您获得礼品！");
				return;
			}
		}
	}
}
