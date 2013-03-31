package thoughtworks.command;

public class CommandFactory {
	public static Command createCommand(String[] commandString) {
		Command command = null;
		switch (commandString[0]) {
		case "block":
			command = new SetBlock(commandString[1]);
			break;
		case "robot":
			command = new SetRobot();
			break;
		case "bomb":
			command = new SetBomb(commandString[1]);
			break;
		case "sell":
			command = new SellFixedAsset(commandString[1]);
			break;
		case "sellTool":
			command = new SellTool(commandString[1]);
			break;
		case "query":
			command = new Query();
			break;
		case "help":
			command = new Help();
			break;
		case "roll":
			command = new Roll();
			break;
		case "quit":
			System.exit(0);
		default:
			System.out.println("指令输入错误！");
		}
		return command;
	}
	
	public static boolean isCommandStringLengthRight(String[] commandStrings) {
		switch (commandStrings[0]) {
		case "block":
		case "bomb":
		case "sell":
		case "sellTool":
			if(commandStrings.length < 2){
				return false;
			}
		}
		return true;
	}
}
