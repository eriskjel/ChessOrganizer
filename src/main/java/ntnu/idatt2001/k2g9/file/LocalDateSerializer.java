package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Serializer class that formats the LocalDate object properly within the JSON file.
 */
public class LocalDateSerializer extends StdSerializer<LocalDate>{
    public LocalDateSerializer(){
        super(LocalDate.class);
    }

    @Override
    /**
     * Serializes the object in the ISO_LOCAL_DATE format.
     */
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException, IOException {
        gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
