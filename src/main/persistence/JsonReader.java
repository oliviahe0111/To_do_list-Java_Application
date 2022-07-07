package persistence;

import model.Work;
import model.WorkList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workList from JSON data stored in file
// Reference: JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(
                Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workList from JSON object and returns it
    private WorkList parseWorkList(JSONObject jsonObject) {
        WorkList wl = new WorkList();
        addWorkList(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wl
    // EFFECTS: parses workList from JSON object and adds them to WorkList
    private void addWorkList(WorkList wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workList");
        for (Object json : jsonArray) {
            JSONObject nextWork = (JSONObject) json;
            addWork(wl, nextWork);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses work from JSON object and adds it to workList
    private void addWork(WorkList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String dueDate = jsonObject.getString("dueDate");
        Boolean done = jsonObject.getBoolean("done");
        Work work = new Work(name, dueDate, done);
        wl.addWork(work);
    }

}
