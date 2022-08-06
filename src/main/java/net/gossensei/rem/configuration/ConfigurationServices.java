package net.gossensei.rem.configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import net.gossensei.rem.Rem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Getter
public class ConfigurationServices {

    private Gson gson;
    private Reader reader;

    private Object obj;

    public ConfigurationServices(String file) {
        try {
            JSONParser parser = new JSONParser();
            //this.reader = Files.newBufferedReader(Paths.get(fileLocation));
            this.obj = parser.parse(new FileReader(file));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String entry) {
        JSONObject object = (JSONObject) obj;
        return object.get("token").toString();
    }

}
