package thoughtworks.command;

public class CommandFactory {
	public static Command createCommand(String commandString) {
		Command command = null;
		switch (commandString) {
		case "block":
			command = new SetBlock();
			break;
		case "robot":
			command = new SetRobot();
			break;
		case "bomb":
			command = new SetBomb();
			break;
		case "sell":
			command = new SellFixedAsset();
			break;
		case "sellTool":
			command = new SellTool();
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
}
