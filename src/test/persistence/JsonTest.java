package persistence;

import model.Work;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Reference: JsonSerializationDemo
public class JsonTest {
    protected void checkWork(String name, String dueDate, boolean done, Work work) {
        assertEquals(name, work.getName());
        assertEquals(dueDate, work.getDueDate());
        assertEquals(done, work.isDone());
    }
}
