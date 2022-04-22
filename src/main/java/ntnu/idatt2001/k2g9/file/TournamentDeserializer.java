package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ntnu.idatt2001.k2g9.player.Admin;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.Match;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentDeserializer extends StdDeserializer{

    public TournamentDeserializer() {
        this(null);
    }

    public TournamentDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException{
        JsonNode node = jp.getCodec().readTree(jp);
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        String name = node.get("name").asText();
        String layout = node.get("layout").asText();
        int id = (Integer) node.get("tournamentID").numberValue();
        String date = mapper.convertValue(node.get("date"),String.class);
        Admin admin = mapper.convertValue(node.get("admin"),Admin.class);
        ArrayList<Match[]> bracket  = new ArrayList<>();
        for (JsonNode matchArrayNode : node.get("tournamentBracket")) {
            bracket.add(mapper.convertValue(matchArrayNode , Match[].class));
        }
        PlayerRegistry players = mapper.convertValue(node.get("playerRegistry"),PlayerRegistry.class);

        //PlayerRegistry players1 = mapper.readValue(pNode.traverse(mapper), PlayerRegistry.class);
        return new Tournament(name,LocalDate.parse(date),bracket,layout,admin,id,players);
    }
}
