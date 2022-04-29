package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that de-serializes a PlayerRegistry object.
 */
public class PlayerRegistryDeserializer extends StdDeserializer{

    public PlayerRegistryDeserializer() {
        this(null);
    }

    public PlayerRegistryDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Writes the PlayerRegistry out as a JSON formatted String.
     * @param jp
     * @param deserializationContext
     * @return
     * @throws IOException
     */
    @Override
    public Object deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ArrayList<Player> playerList = new ArrayList<>();
        List<JsonNode> list = node.findValues("players");
        ObjectMapper om = new ObjectMapper();
        for(int i = 0; i<list.get(0).size();i++){
            playerList.add(om.convertValue(list.get(0).get(i),Player.class));
        }
        return new PlayerRegistry(playerList);
    }
}
