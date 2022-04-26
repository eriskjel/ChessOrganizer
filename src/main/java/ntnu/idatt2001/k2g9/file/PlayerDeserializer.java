package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ntnu.idatt2001.k2g9.player.Player;

import java.io.IOException;

/**
 * Class that de-serializes a JSON string containing a player into a Player object.
 */
public class PlayerDeserializer extends StdDeserializer{
    public PlayerDeserializer() {
        this(null);
    }

    public PlayerDeserializer(Class<?> vc) {
        super(vc);
    }


    /**
     * Method creates a player object from the JSON input.
     * @param jp
     * @param deserializationContext
     * @return
     * @throws IOException
     */
    @Override
    public Object deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        int age = (Integer) node.get("age").numberValue();
        int playerID = (Integer) node.get("playerID").numberValue();
        return new Player(name,age,playerID);
    }
}
