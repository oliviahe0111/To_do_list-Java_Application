package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//Represent a list having Work
public class WorkList implements Writable {

    private final ArrayList<Work> workList;

    /*
     * EFFECTS: create a new workList with no work in it
     */
    public WorkList() {
        workList = new ArrayList<>();
    }

    /*
     * REQUIRES: no duplicate name work
     * MODIFIES: this
     * EFFECTS: add a new work in the workList
     */
    public void addWork(Work work) {
        workList.add(work);
        EventLog.getInstance().logEvent(new Event("Work named \"" + work.getName() + "\" is added."));
    }

    // EFFECTS: returns an unmodifiable list of works in this workList
    public List<Work> getAllWork() {
//        EventLog.getInstance().logEvent(new Event("All work from the workList was accessed."));
        return Collections.unmodifiableList(workList);
    }

    /*
     * MODIFIES: unfinishedWork
     * EFFECTS: return a new workList that consist of all the work
     *          that has unfinished status
     */
    public ArrayList<Work> getUnfinishedWork() {
        ArrayList<Work> unfinishedWork = new ArrayList<>();
        for (Work work : workList) {
            if (!work.isDone()) {
                unfinishedWork.add(work);
            }
        }
//        EventLog.getInstance().logEvent(new Event("All unfinished works was accessed."));

        return unfinishedWork;

    }

    /*
     * REQUIRES: name has a non-zero length, no duplicate name work
     * MODIFIES: this
     * EFFECTS: remove the work in the list that has the same name
     *          as the passed in name
     */
    public void deleteSelectedWork(String name) {
        for (int i = 0; i < workList.size(); i++) {
            if (workList.get(i).getName().equals(name)) {
                EventLog.getInstance().logEvent(new Event(
                        "Work named \"" + workList.get(i).getName() + "\" is deleted."));
                workList.remove(workList.get(i));
                return;
            }
        }
    }

    /*
     * REQUIRES: name has a non-zero length, no duplicate name work
     * MODIFIES: this
     * EFFECTS: set the status of a work that has the same
     *          name as passed in name to true (finished)
     */
    public void markDone(String name) {
        for (Work next : workList) {
            if (next.getName().equals(name)) {
                next.setDone(true);
                EventLog.getInstance().logEvent(new Event(
                        "Work named \"" + next.getName() + "\" is marked done."));
                return;
            }
        }
    }

    /*
     * MODIFIES: sb
     * EFFECTS: return a String sb, sb will be either a String saying no unfinished work in the
     *          workList or a String showing all the unfinished work in a designed
     *          format depending on the work status in the workList
     */
    public String printUnfinishedWorkMessage() {
        ArrayList<Work> workList = getUnfinishedWork();
        StringBuilder sb = new StringBuilder();
        if (workList.isEmpty()) {
            return "You have finished all the work!";
        } else {
            sb.append("Here are the unfinished work:\n");
            for (Work next : workList) {
                String oneLine = next.getName() + " is due on " + next.getDueDate() + "\n";
                sb.append(oneLine);
            }
        }
        return sb.toString();
    }

    /*
     * MODIFIES: sb
     * EFFECTS: return a String sb, sb will be either a String saying no work in the
     *          workList or a String showing all the work in a designed
     *          format depending on the work status in the workList
     */
    public String printAllWorkMessage() {
        String status;
        StringBuilder sb = new StringBuilder();
        if (workList.isEmpty()) {
            return "No work is in the list!";
        } else {
            sb.append("Here are all the work:\n");
            for (Work next : workList) {
                if (next.isDone()) {
                    status = "finished it.";
                } else {
                    status = "not finished it.";
                }
                String oneLine = next.getName() + " is due on " + next.getDueDate()
                        + ", and you have " + status + "\n";
                sb.append(oneLine);
            }
        }
        return sb.toString();
    }

    /*
     * EFFECTS: return the number of work in the workList
     */
    public int size() {
        return workList.size();
    }

    /*
     * EFFECTS: return the status of the passed in work
     */
    public boolean contains(Work work) {
        return workList.contains(work);
    }

    @Override
    // Reference: JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workList", workListToJson());
        return json;
    }

    // EFFECTS: returns things in this workList as a JSON array
    // Reference: JsonSerializationDemo
    private JSONArray workListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Work w : workList) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }

    /*
     * EFFECTS: return all the Events logged
     */
    public void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }


}

