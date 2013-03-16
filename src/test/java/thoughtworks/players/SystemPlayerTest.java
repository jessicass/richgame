package thoughtworks.players;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SystemPlayerTest {

    @Test
    public void shouldGetCurentSystemPlayerById() throws Exception {
        // when
        SystemPlayer systemPlayer = SystemPlayer.getPlayer(1);

        // then
        assertThat(systemPlayer, is(SystemPlayer.Q));
    }
}
