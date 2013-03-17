package thoughtworks.publicPlace;

import org.junit.Test;
import thoughtworks.players.Player;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 3/17/13
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class MagicRoomTest {
    @Test
    public void shouldGetCurrentSymbol() throws Exception {
        // given
        MagicRoom magicRoom = new MagicRoom(1);

        // when
        String symbol = magicRoom.getSymbol(new ArrayList<Player>());

        // then
        assertThat(symbol, is("M"));
    }
}
