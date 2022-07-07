package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.Work;
import model.WorkList;
import persistence.JsonReader;
import persistence.JsonWriter;

// Deadline Tracker application
public class DeadlineTrackerApp {
    private static final String JSON_STORE = "./data/workList.json";
    private Scanner input = new Scanner(System.in).useDelimiter("\n");
    private WorkList workList = new WorkList();
    private boolean toContinue = true;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /*
     * EFFECTS: run the WorkApp
     * REFERENCES： TellerApp
     */
    public DeadlineTrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWorkApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: take user input
     * REFERENCES： TellerApp
     */
    public void runWorkApp() {
        String selection = null;

        while (toContinue) {
            displayMenu();
            selection = input.next();
            processSelection(selection);
        }
    }

    /*
     * EFFECTS: displays menu of options to user
     * REFERENCES： TellerApp
     */
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add a new work");
        System.out.println("\t2 -> See all unfinished work");
        System.out.println("\t3 -> See all the work");
        System.out.println("\t4 -> Delete a work");
        System.out.println("\t5 -> Mark a work as done");
        System.out.println("\t6 -> Save workList to file");
        System.out.println("\t7 -> Load workList from file");
        System.out.println("\t8 -> Quit");
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user command
     * REFERENCES： TellerApp
     */
    private void processSelection(String selection) {
        if (selection.equals("1")) {
            makeNewWork();
        } else if (selection.equals("2")) {
            showUnfinishedWork();
        } else if (selection.equals("3")) {
            showAllWork();
        } else if (selection.equals("4")) {
            deleteWork();
        } else if (selection.equals("5")) {
            markWorkFinished();
        } else if (selection.equals("6")) {
            saveWorkList();
        } else if (selection.equals("7")) {
            loadWorkList();
        } else if (selection.equals("8")) {
            toContinue = false;
            System.out.println("\nBye!");
        } else {
            System.out.println("Invalid selection...");
        }
    }

    // EFFECTS: saves the workList to file
    // Reference: JsonSerializationDemo
    private void saveWorkList() {
        try {
            jsonWriter.open();
            jsonWriter.write(workList);
            jsonWriter.close();
            System.out.println("Saved workList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workList from file
    // Reference: JsonSerializationDemo
    private void loadWorkList() {
        try {
            workList = jsonReader.read();
            System.out.println("Loaded workList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // REQUIRES: the work is unfinished
    // MODIFIES: this
    // EFFECTS: change the work's state from unfinished to finished
    private void markWorkFinished() {
        System.out.println("Please enter the name of the work you want to mark as done:");
        String name = input.next();
        workList.markDone(name);
        System.out.println("Work marked done successfully!");
    }

    /*
     * EFFECTS: remove the entered work in the list
     */
    private void deleteWork() {
        System.out.println("Please enter the name of the work you want to delete:");
        String name = input.next();
        workList.deleteSelectedWork(name);
        System.out.println("Work deleted successfully!");
    }

    /*
     * EFFECTS: show all work in the list
     */
    private void showAllWork() {
        System.out.println(workList.printAllWorkMessage());
    }

    /*
     * EFFECTS: show all the unfinished work in the list
     */
    private void showUnfinishedWork() {
        System.out.println(workList.printUnfinishedWorkMessage());
    }

    /*
     * MODIFIES: workList
     * EFFECTS: add a new work to the workList
     */
    private void makeNewWork() {
        System.out.println("Please enter the name of the work you want to add:");
        String name = input.next();
        System.out.println("Please enter the deadline of the work in \"yyyy-MM-dd\" form:");
        String deadline = input.next();
        workList.addWork(new Work(name, deadline, false));
        System.out.println("Work added successfully!");
    }

}