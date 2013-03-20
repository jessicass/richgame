package thoughtworks;

import thoughtworks.command.*;
import thoughtworks.fixedAssets.Space;
import thoughtworks.functionClass.Input;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.functionClass.RoleNumberTransfer;
import thoughtworks.players.*;

public class Game {
	public static final String HINT_OF_START = "请输入游戏开始指令：";
	public static final String ERROR_OF_START = "指令错误，请重新输入游戏开始指令：";
	
	public static final String START_COMMAND = "rich";

	private PlayerList playerList;
	private Map map = new Map();
	private CommandManager commandManager = new CommandManager();

	public PlayerList getPlayerList() {
		return playerList;
	}
	
	public Map getMap() {
		return map;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void start() {
		System.out.println(HINT_OF_START + "\n");
		while (true) {
			if (Input.isInputCommandEquals(START_COMMAND)) {
				PlayerList.obtainInputToInitialFunds();
				CreatPlayerList(PlayerList.obtainInputToCreatePlayerList());
				gameProcess();
				break;
			} else {
				System.out.println(ERROR_OF_START + "\n");
			}
		}
	}

	public void CreatPlayerList(String input) {
		playerList = new PlayerList(RoleNumberTransfer.
				transferInputToRoleNumberArray(input));
	}

	public void gameProcess() {
		while (true) {			
			for (Player player : playerList.getPlayers()) {
				map.drawMap(playerList.getPlayers());
				if(isWinnerProduced()){					
					return;
			    }
				
				decreaseTimes(player);
				
				if (player.isBombed() || player.isTrappedInPrison() ||
						player.isBankrupt()) {
					continue;
				}
				
				while (true) {
					System.out.println(player.getName() + ">");
					String input = Input.getString();
					if (commandManager.isCommandRunEnd(input, player, this)) {
						break;
					}
				}
				
				if (!isPlayerPassOnSpaceOwned(player)) {
					map.getMapObjectWithIndex(player.getPosition())
							.playerPassOnHere(player, this);
					continue;
				}
				Space space = (Space)map.getMapObjectWithIndex(player.getPosition());
				if (space.isOwnedBy(player)) {
					playerPassOnOwnSpace(player);
					continue;
				}
				playerPassOnOtherSpace(player);
			}
		}
	}

	private void decreaseTimes(Player player) {
		player.decreaseHospitalizedTimes();
		player.decreaseLuckyTimes();
		player.decreaseTrappedInPrisonTimes();
	}
	
	private boolean isWinnerProduced() {
		int playerNumber = 0;
		for (Player player : playerList.getPlayers()) {
			if(playerNumber > 1){
				return false;
			}
			if(player.isBankrupt()){
				continue;
			}
			playerNumber++;
		}
		System.out.println("恭喜玩家" + getWinner().getName() + "获胜！");
		return true;
	}
	
	private Player getWinner() {
		for (Player player : playerList.getPlayers()) {
			if(!player.isBankrupt()){
				return player;
			}
		}
		return null;
	}

	public boolean isPlayerPassOnSpaceOwned(Player player){
		if(map.isSpaceWithPositionOf(player.getPosition())){
			Space space = (Space)map.getMapObjectWithIndex(player.getPosition());
			if(space.isOwned()){
				return true;
			}
		}
		return false;
	}
	
	public void playerPassOnOwnSpace(Player player) {
		Space space = (Space) map.getMapObjectWithIndex(player.getPosition());
		if (space.isPlayerToUpgradeOwnSpace(player)) {
			upgradeFixedAssetsWithPositionOf(player.getPosition());
		}
		return;
	}
	
	public void playerPassOnOtherSpace(Player player) {
		Space space = (Space) map.getMapObjectWithIndex(player.getPosition());
		while (!space.isSafeForPlayerPassOnOtherSpace(player)) {
			player.updateBankruptState(space.getPassToll());
			if (player.isBankrupt()) {
				System.out.println("玩家" + player.getName() + "破产");
				playerList.getPlayers().remove(player);
			} else {
				System.out.println("请输入要出售的房产编号：");
				commandManager.sellFixedAssetsWithCommand(Input.getString(), player, this);
			}
		}
	}

	public void updatePlayerPosition(Player player, int step) {
		int position = player.getPosition();
		for (int i = 0; i < step; i++) {
			position = PositionUpdate.getNextPosition(position);
			if (map.getMapObjectWithIndex(position).hasBlock()) {
				System.out.println("非常不幸，被路障拦截！");
				player.updatePosition(position);
				map.getMapObjectWithIndex(position).resetBlock();
				return;
			}
			if (map.getMapObjectWithIndex(position).hasBomb()) {
				System.out.println("非常不幸，被炸弹炸伤！");
				player.updatePosition(GlobalSettings.HosipitalPosition);
				player.toBeBombed();
				map.getMapObjectWithIndex(position).resetBomb();
				return;
			}
		}
		player.updatePositionWithStep(step);
	}

	public void upgradeFixedAssetsWithPositionOf(int position){
		Space oldSpace = (Space)map.getMapObjectWithIndex(position);
		Space newSpace = getUpgradeSpace(oldSpace);
		if (newSpace != null) {
            oldSpace.getOwner().upgradeOwnFixedAssets(
                    oldSpace);
			map.upgradeFixedAssets(newSpace);
			System.out.println("房产升级成功！");
		}
	}
	
	public void sellSpaceWithPositionOf(int position){
		Space oldSpace = (Space)map.getMapObjectWithIndex(position);
        Player player = oldSpace.getOwner();
		Space newSpace = oldSpace.sell();
		player.sellSpace(oldSpace);
		map.upgradeFixedAssets(newSpace);
	}

	public Space getUpgradeSpace(Space space){
		return space.upgrade();
	}
}
