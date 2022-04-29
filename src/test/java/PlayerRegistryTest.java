import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerRegistryTest {

    @Test
    void addPlayerTest(){
        PlayerRegistry players = new PlayerRegistry();
        players.addPlayer(new Player("Daniel",20));
        Assertions.assertEquals(players.getSize(),1);
    }

}
