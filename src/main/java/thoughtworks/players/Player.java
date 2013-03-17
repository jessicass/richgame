package thoughtworks.players;

import java.awt.Color;

import thoughtworks.GlobalSettings;
import thoughtworks.Map;
import thoughtworks.fixedAssets.*;
import thoughtworks.functionClass.PositionUpdate;
import thoughtworks.publicPlace.*;

public class Player {
    private int funds;
    private int points = 0;
    private int position;
    private boolean isBombed = false;
    private boolean isOwnerOfLuck = false;
    private boolean isTrappedInPrison = false;
    private boolean isBankrupt = false;
    private int timesToPauseWhenBeBombed =
            Hospital.timesToPauseWhenPlayerBeHospitalized + 1;
    private int timesForFreeWhenOwnLuck = 5 + 1;
    private int timesBeTrappedInPrison =
            Prison.timesPlayerBeTrappedInPrison + 1;

    private FixedAssets fixedAssetsOfPlayer =
            new FixedAssets();
    private Tools toolsOfPlayer = new Tools();

    private SystemPlayer info;

    public Player(int index) {
        info = SystemPlayer.getPlayer(index);
        funds = GlobalSettings.INITIAL_FUNDS;
    }

    public Color getColor() {
        return info.getColor();
    }
    
    public String getName() {
    	return info.getName();
    }
    
    public String getShortName() {
    	return info.toString();
    }

    public int getFunds() {
        return funds;
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public FixedAssets getFixedAssetsOfPlayer() {
        return fixedAssetsOfPlayer;
    }

    public Tools getToolsOfPlayer() {
        return toolsOfPlayer;
    }

    public void updatePosition(int position) {
        this.position = position;
    }

    public void updatePositionWithStep(int step) {
        position = PositionUpdate.getCurrentPositionWithDistance(position, step);
    }

    public void buySpace(int buyFunds) {
        funds -= buyFunds;
        fixedAssetsOfPlayer.addNewSpace();
    }

    public void sellSpace(Space space) {
        funds += space.getTotalCost() * 2;
        fixedAssetsOfPlayer.sellSpace(space.getLevel());
    }

    public void buyTool(int toolNumber) {
        points -= ToolRoom.buyToolPoints(toolNumber);
        toolsOfPlayer.buyTool(toolNumber);
    }

    public void chooseGift(int giftNumber) {
        switch (giftNumber) {
            case 1:
                funds += GiftRoom.presentFunds;
                return;
            case 2:
                points += GiftRoom.presentPoints;
                return;
            case 3:
                isOwnerOfLuck = true;
                return;
        }
    }

    public void decreaseLuckyTimes() {
        if (isOwnerOfLuck) {
            timesForFreeWhenOwnLuck--;
            if (timesForFreeWhenOwnLuck == 0) {
                isOwnerOfLuck = false;
                timesForFreeWhenOwnLuck = 5 + 1;
            }
        }
    }

    public void decreaseHospitalizedTimes() {
        if (isBombed) {
            timesToPauseWhenBeBombed--;
            if (timesToPauseWhenBeBombed == 0) {
                isBombed = false;
                timesToPauseWhenBeBombed =
                        Hospital.timesToPauseWhenPlayerBeHospitalized + 1;
            }
        }
    }

    public void decreaseTrappedInPrisonTimes() {
        if (isTrappedInPrison) {
            timesBeTrappedInPrison--;
            if (timesBeTrappedInPrison == 0) {
                isTrappedInPrison = false;
                timesBeTrappedInPrison =
                        Prison.timesPlayerBeTrappedInPrison + 1;
            }
        }
    }

    public void toBeBombed() {
        isBombed = true;
        position = Map.HosipitalPosition;
    }

    public void toBeTrappedInPrison() {
        isTrappedInPrison = true;
    }

    public boolean isBombed() {
        return isBombed;
    }

    public boolean isOwnerOfLuck() {
        return isOwnerOfLuck;
    }

    public boolean isTrappedInPrison() {
        return isTrappedInPrison;
    }

    public void upgradeOwnFixedAssets(Space space) {
        int upgradeFunds = space.getUpgradeFunds();
        funds -= upgradeFunds;
        fixedAssetsOfPlayer.upgradeSpace(space.getLevel());
    }

    public void handInPassTollToOthers(int passToll) {
        funds -= passToll;
    }

    public void obtainPassTollFromOthers(int passToll) {
        funds += passToll;
    }

    public void obtainPointsFromMine(int points) {
        this.points += points;
    }

    public void sellToolWithNumberOf(int toolNumber) {
        this.points += toolsOfPlayer.getPointsOfToolWithNumberOf(toolNumber);
        toolsOfPlayer.decreaseNumberOfTools(toolNumber);
    }

    public void testBankrupt(int passTool) {
        if ((fixedAssetsOfPlayer.getTotalNumberOfFixedAssets() <= 0) &&
                (funds < passTool)) {
            isBankrupt = true;
        }
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }
}
