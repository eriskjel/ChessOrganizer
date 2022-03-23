import ntnu.idatt2001.k2g9.Player;
import ntnu.idatt2001.k2g9.PlayerRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerRegistryTest {

    @Test
    void addPlayerTest(){
        PlayerRegistry players = new PlayerRegistry();
        players.addPlayer("Daniel",19);
        players.addAdmin("Erik",20,"eriskje@ntnu.no","123");
        players.addUser("Eirik",19,"eiriklta@ntnu.no","456");
        Assertions.assertEquals(players.getSize(),3);
        System.out.println(players);
    }

}
