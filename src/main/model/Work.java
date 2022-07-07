package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a work having a work name, the work due date and a boolean indicating if the work is finished or not
public class Work implements Writable {
    private String name;   //work name
    private String dueDate;    //the work due date
    private boolean done;      //true if the work is done

    /*
     * REQUIRES: workName has a non-zero length, dueDate has a pattern of "yyyy-MM-dd"
     * EFFECTS: name of the work is set to workName; deadline of the work
     *          is set to dueDate as String; the boolean done is true if the
     *          task is done
     */
    public Work(String name, String dueDate, boolean done) {
        this.name = name;
        this.dueDate = dueDate;
        this.done = done;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("dueDate", dueDate);
        json.put("done", done);
        return json;
    }
}