package thoughtworks;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import thoughtworks.fixedAssets.Space;
import thoughtworks.players.Player;

public class PlayerRestOnOtherSpaceTest {
	@Test
	public void shouldFundsOfPPassSpaceOfOtherslayerReduce100(){
		Player.INITIAL_FUNDS = 10000;
		Space space = new Space(2);
		int ownerNumber = 1;
		int passerNumber = 2;
		int[] playerNumbers = {ownerNumber , passerNumber};
		PlayerList playerList = new PlayerList(playerNumbers);
		playerList.getPlayer(ownerNumber).buySpace(space);
		playerList.playerPassOnOtherSpace(playerList.getPlayer(passerNumber), 
				space);
		assertThat(playerList.getPlayer(ownerNumber).getFunds() , 
				is(10000-200+100));
		assertThat(playerList.getPlayer(passerNumber).getFunds() , 
				is(10000-100));
	}
}
