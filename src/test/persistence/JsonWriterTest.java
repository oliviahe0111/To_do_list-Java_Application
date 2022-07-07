package persistence;

import model.Work;
import model.WorkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Reference: JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            WorkList wl = new WorkList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WorkList wl = new WorkList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkList.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkList.json");
            wl = reader.read();
            assertEquals(0, wl.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkList wl = new WorkList();
            wl.addWork(new Work("stat", "2020-01-01", false));
            wl.addWork(new Work("cpsc", "2021-11-11", true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkList.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkList.json");
            wl = reader.read();
            List<Work> workList = wl.getAllWork();
            assertEquals(2, workList.size());
            checkWork("stat","2020-01-01",false,workList.get(0));
            checkWork("cpsc","2021-11-11",true,workList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
