package ui;

import model.Event;
import model.EventLog;
import model.WorkList;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;

//Run the Quit window view
public class QuitView extends JDialog {

    private static final String JSON_STORE = "./data/workList.json";
    private JsonWriter writer;

    /*
     * EFFECTS: display the window
     * MODIFIES: this
     */
    public QuitView(AppView appView, WorkList workList) {
        super(appView, "Quit", true);



        IconImage icon1 = new IconImage(appView,"src/main/ui/images/saveIcon.png","Save changes?","Quit");
        int option = icon1.optionIcon();

        if (option == JOptionPane.YES_OPTION) {
            writer = new JsonWriter(JSON_STORE);
            try {

                writer.open();
                JsonWriter.write(workList);
                writer.close();
                appView.dispose();

                IconImage icon = new IconImage(appView, "src/main/ui/images/addIcon.png",
                        "Changes saved successfully!", "Quit");
                icon.regularIcon();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        workList.printLog();
        System.exit(0);
    }


}

