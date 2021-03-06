package thoughtworks.command;

import thoughtworks.Game;
import thoughtworks.players.Player;

public class Help extends Command {
	public static final String COMMAND_TABLE = 
			"roll" + "\t\t" + "掷骰子命令，行走1~6步。" + "\n" +  
			"block n" + "\t\t" + "玩家拥有路障，可将路障放置到离当前位置" +
					"前后10步的距离，任一玩家经过路障，都将被拦截。该道具" +
			        "一次有效。n代表前后的相对距离，负数表示后方。" + "\n" +
			"bomb n" + "\t\t" + "可将炸弹放置到离当前位置前后10步的距离，" +
			        "任一玩家经过该位置，都将被炸伤并送往医院，住院三天" +
			        "（即轮空三次）。n代表前后的相对距离，负数表示后方。" + "\n" +
			"robot" + "\t\t" + "使用该道具可清扫前方路面上10步以内的道具，" +
			   		"如炸弹、路障。" + "\n" +
			"sell x" + "\t\t" + "出售自己的房产，x代表地图上的绝对位置，" +
			   		"即地产的编号。" + "\n" +
			"sellTool x" + "\t" + "出售道具，x代表道具编号。" + "\n" +
			"query" + "\t\t" + "显示自家资产信息。" + "\n" + 
			"help" + "\t\t" + "查看命令帮助。" + "\n" +
			"quit" + "\t\t" + "强制退出。" + "\n";

	@Override
	public void commandExecute(Player player, Game game) {
		System.out.println(COMMAND_TABLE);		
	}
}
