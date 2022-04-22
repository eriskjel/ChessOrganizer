package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeserializerTest {

    @Test
    public void playerDeserializer() throws JsonProcessingException {
        Player p = new Player("Daniel",20);
        String serialized = new ObjectMapper().writeValueAsString(p);
        JsonNode read = new ObjectMapper().readTree(serialized);
        System.out.println(serialized);
        Player p2 = new ObjectMapper().convertValue(read,Player.class);
        System.out.println(p2);
        Assertions.assertEquals(p,p2);
    }

    @Test
    public void playerRegistryDeserializer() throws JsonProcessingException {
        PlayerRegistry players = new PlayerRegistry();
        for(int i = 0 ; i<5 ; i++){
            players.addPlayer(new Player("Test"+ Integer.toString(i),20+i));
        }
        String serialized = new ObjectMapper().writeValueAsString(players);
        System.out.println(serialized);
        JsonNode read = new ObjectMapper().readTree(serialized);
        PlayerRegistry players2 = new ObjectMapper().convertValue(read,PlayerRegistry.class);
        System.out.println(players2);
        System.out.println(serialized);
    }

    @Test
    public void tournamentRegistryDeserializer() throws JsonProcessingException {
        Tournament t = new Tournament("NTNU", LocalDate.parse("2018-10-23"),"Knock-Out");
        for(int i = 0 ; i<5 ; i++){
            t.addPlayer(new Player("Test"+ Integer.toString(i),20+i));
        }
        t.createTournamentBracket();
        String serialized = new ObjectMapper().writeValueAsString(t);
        System.out.println(serialized);
        JsonNode read = new ObjectMapper().readTree(serialized);
        System.out.println(read.toString());
        Tournament t2 = new ObjectMapper().convertValue(read,Tournament.class);
        Assertions.assertEquals(t,t2);
    }

}
