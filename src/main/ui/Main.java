package ui;

import model.WorkList;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import java.io.IOException;

// Run Deadline tracker app
public class Main {
    private static final String JSON_STORE = "./data/workList.json";
    private static JsonReader reader;

    public static void main(String[] args) throws IOException {
//        phase 2:
//        try {
//            new DeadlineTrackerApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
//
        /*
         * EFFECTS: set load JOptionPane icon and allow user to choose load logged files or not
         * MODIFIES: this
         */
        IconImage icon = new IconImage(null,"src/main/ui/images/loadIcon.png",
                "Load tasks?","Load Data");
        int option = icon.optionIcon();
        if (option == JOptionPane.YES_OPTION) {
            reader = new JsonReader(JSON_STORE);
            WorkList workList = reader.read();

            new AppView(workList);
        } else {
            new AppView(new WorkList());
        }

    }
}
