package ui;

import model.Work;
import model.WorkList;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Run the addNewTask window view
public class AddView extends JDialog implements ActionListener {
    private WorkList workList;
    private AppView appView;
    private Box nameBox;
    private Box dueDateBox;
    private Box statusBox;
    private JTextField nameField;
    private JTextField dueDateField;

    private boolean state;
    private JRadioButton finishedButton;

    /*
     * EFFECTS: display the window
     * MODIFIES: this
     */
    public AddView(AppView appView, WorkList workList) {
        super(appView, "Add New Task", true);
        this.appView = appView;
        this.workList = workList;


        Dimension appViewSize = appView.getSize();
        Point p = appView.getLocation();
        setLocation(p.x + appViewSize.width / 4, p.y + appViewSize.height / 4);

        JPanel buttonPane = new JPanel();
        Color color = new Color(165, 255, 249, 128);
        getContentPane().setBackground(color);


        buttonPane.setBackground(color);
        this.formatButtonsAndFields();


        JButton addButton = new JButton("Add");
        buttonPane.add(addButton);
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);


        JButton cancelWindow = new JButton("Cancel");
        cancelWindow.setActionCommand("cancel");
        cancelWindow.addActionListener(this);
        buttonPane.add(cancelWindow);

        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /*
     * EFFECTS: format boxes addView
     * MODIFIES: this
     */
    public void formatButtonsAndFields() {
        Box verticalBoxBox = Box.createVerticalBox();

        this.setButtonsAndFields();

        verticalBoxBox.add(Box.createVerticalStrut(20));
        verticalBoxBox.add(nameBox);
        verticalBoxBox.add(Box.createVerticalStrut(15));
        verticalBoxBox.add(dueDateBox);
        verticalBoxBox.add(Box.createVerticalStrut(15));
        verticalBoxBox.add(statusBox);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalStrut(20));
        horizontalBox.add(verticalBoxBox);
        horizontalBox.add(Box.createHorizontalStrut(20));

        add(horizontalBox);
    }

    /*
     * EFFECTS: set up three boxes with the corresponding button and field (name, dueDate, status)
     * MODIFIES: this
     */
    public void setButtonsAndFields() {
        //name
        nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("Task Name: ");
        nameField = new JTextField(15);

        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //dueDate
        dueDateBox = Box.createHorizontalBox();
        JLabel dueDateLabel = new JLabel("Due Date: ");
        dueDateField = new JTextField(15);

        dueDateBox.add(dueDateLabel);
        dueDateBox.add(Box.createHorizontalStrut(20));
        dueDateBox.add(dueDateField);

        //status
        statusBox = Box.createHorizontalBox();
        finishedButton = new JRadioButton("finished!");
        finishedButton.setActionCommand("finished!");
        JRadioButton unfinishedButton = new JRadioButton("not finished!");
        unfinishedButton.setActionCommand("not finished!");
        ButtonGroup group = new ButtonGroup();
        group.add(finishedButton);
        group.add(unfinishedButton);
        statusBox.add(finishedButton);
        statusBox.add(unfinishedButton);
        this.setState();

    }

    /*
     * EFFECTS: set the finished state of the task corresponding to ActionEvent
     * MODIFIES: this
     */
    public void setState() {
        this.finishedButton.addActionListener(e -> {
            String action = e.getActionCommand();
            state = action.equals("finished!");
        });
    }

    /*
     * EFFECTS: allow all the Add and cancel Buttons respond to ActionEvent
     * MODIFIES: this
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if (action.equals("Add")) {

            this.addButtonAction(state);

        } else if (e.getActionCommand().equals("cancel")) {
            dispose();
        }
    }

    /*
     * EFFECTS: add new Work in the workList corresponding to what is entered in JTextField and selected in JRadioButton
     * MODIFIES: this
     */
    public void addButtonAction(boolean state) {
        String name = nameField.getText();
        String dueDate = dueDateField.getText();
        workList.addWork(new Work(name, dueDate, state));
        dispose();
        appView.dispose();
        new AppView(workList);

        IconImage icon = new IconImage(appView, "src/main/ui/images/addIcon.png",
                "Added successfully", "Add");
        icon.regularIcon();
    }
}


