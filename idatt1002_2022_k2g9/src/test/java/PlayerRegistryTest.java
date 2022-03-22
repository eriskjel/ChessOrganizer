import ntnu.idatt2001.k2g9.Player;
import ntnu.idatt2001.k2g9.PlayerRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerRegistryTest {
    @Test
    public void testGetPlayer(){
        Player testPlayer = new Player("daniesky@ntnu.no","123","Daniel",2000,19,"God",100,0);
        Player decoyPlayer = new Player("eriskjel@ntnu.no","456","Erik",15,20,"Dirt",0,100);
        PlayerRegistry players = new PlayerRegistry();
        players.addPlayer(testPlayer);
        players.addPlayer(decoyPlayer);
        Player search = players.getPlayer("daniesky@ntnu.no","123");
        Assertions.assertEquals(search, testPlayer);
    }
}
