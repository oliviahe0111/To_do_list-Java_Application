package persistence;

import model.Work;
import model.WorkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Reference: JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkList wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkList.json");
        try {
            WorkList wl = reader.read();
            assertEquals(0, wl.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkList.json");
        try {
            WorkList wl = reader.read();
            List<Work> workList = wl.getAllWork();
            assertEquals(2, workList.size());
            checkWork("stat","2020-01-01",false,workList.get(0));
            checkWork("cpsc","2021-11-11",true,workList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
