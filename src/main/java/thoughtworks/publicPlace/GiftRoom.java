package thoughtworks.publicPlace;

import thoughtworks.Game;
import thoughtworks.GlobalSettings;
import thoughtworks.MapObject;
import thoughtworks.functionClass.Input;
import thoughtworks.players.Player;

public class GiftRoom extends Terrain implements MapObject {
	public static final String symbol = "G";
	public static final String WELCOME = "欢迎光临礼品屋，请输入" +
			"您所需要的礼品的编号：";
	
	public GiftRoom(int position) {
		super(position);
	}
	
    @Override
	protected String getSymbol(){
		return symbol;
	}
	
	public static String giftInfoShow() {
		String info = "礼品" + "\t" + "编号" + "\n";
		info += "奖金" + "\t" + "1" + "\n";
		info += "点数卡" + "\t" + "2" + "\n";
		info += "福神" + "\t" + "3" + "\n";
		return info;	
	}
	
	@Override
	public void playerPassOnHere(Player passer, Game game) {
		System.out.println(giftInfoShow());
		System.out.println(WELCOME);
		while(true){
			int input = Input.getInteger();
			if(Input.isIntegerInArea(input, GlobalSettings.MIN_GIFT_NUMBER, 
					GlobalSettings.MAX_GIFT_NUMBER)){
				passer.chooseGift(input);
				System.out.println("恭喜您获得礼品！");
				return;
			}
		}
	}
}
