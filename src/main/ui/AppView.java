package ui;

import model.Work;
import model.WorkList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

//Run the main window view
public class AppView extends JFrame implements ActionListener {
    private JTable table;
    private WorkList workList;
    private DefaultTableModel model;

    /*
     * EFFECTS: display the window
     * MODIFIES: this
     * REFERENCE: https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
     */
    public AppView(WorkList workList) {
        super("Deadline Tracker");
        String[] titlesV = new String[]{"Name", "Due Date", "Finished?"};

        this.workList = workList;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        this.setBackground();

        setLayout(new FlowLayout());

        this.setUpButtons();

        model = new DefaultTableModel(null, titlesV) {
        };
        table = new JTable(model);
        table.setSize(300, 100);
        this.getDataValue();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        add(scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /*
     * EFFECTS: pass in all the work in workList to the JTable row
     * MODIFIES: this, model
     */
    public void getDataValue() {
        for (int i = 0; i < workList.size(); i++) {
            Work work = workList.getAllWork().get(i);
            Object[] data = new Object[]{
                    work.getName(), work.getDueDate(), work.isDone()
            };
            model.addRow(data);
        }
    }

    /*
     * EFFECTS: set panel background
     * MODIFIES: this
     */
    public void setBackground() {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("src/main/ui/images/backgroundImage.png")));
            int width = 500;
            int height = 300;
            icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            setContentPane(new JLabel(icon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * EFFECTS: set up all the buttons on appView
     * MODIFIES: this
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void setUpButtons() {
        JButton addNewTask = new JButton("Add New Task");
        addNewTask.setActionCommand("addNewTask");
        addNewTask.addActionListener(this);
        add(addNewTask);

        JButton markDoneButton = new JButton("Mark Done");
        markDoneButton.setActionCommand("markDoneButton");
        markDoneButton.addActionListener(this);
        add(markDoneButton);

        JButton deleteTask = new JButton("Delete");
        deleteTask.setActionCommand("deleteTask");
        deleteTask.addActionListener(this);
        add(deleteTask);

        JButton quitApp = new JButton("Quit");
        quitApp.setActionCommand("quitApp");
        quitApp.addActionListener(this);
        add(quitApp);

        JButton filterTask = new JButton("Show unfinished tasks");
        filterTask.setActionCommand("filterTask");
        filterTask.addActionListener(this);
        add(filterTask);

        JButton showAllTask = new JButton("Show all tasks");
        showAllTask.setActionCommand("showAllTask");
        showAllTask.addActionListener(this);
        add(showAllTask);
    }

    /*
     * EFFECTS: allow all the JButtons respond to ActionEvent
     * MODIFIES: this
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addNewTask")) {
            new AddView(this, workList);
        } else if (e.getActionCommand().equals("markDoneButton")) {
            this.markDoneAction();
        }  else if (e.getActionCommand().equals("deleteTask")) {
            this.deleteAction();
        } else if (e.getActionCommand().equals("quitApp")) {
            new QuitView(this, workList);
        } else if (e.getActionCommand().equals("filterTask")) {
            this.filterAction();
        } else if (e.getActionCommand().equals("showAllTask")) {
            this.showAllAction();
        }
    }

    /*
     * EFFECTS: implement the deleteButton action
     * MODIFIES: this
     */
    public void deleteAction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            IconImage icon = new IconImage(this, "src/main/ui/images/chooseIcon.png",
                    "Please select a task!", "No Selection");
            icon.regularIcon();
            return;
        }

        IconImage icon1 = new IconImage(null,"src/main/ui/images/deleteIcon.png",
                "Are you sure you want to delete this task?","Confirm Delete");
        int result = icon1.optionIcon();
        if (result != JOptionPane.YES_OPTION) {
            return;
        }
        String name = (String) model.getValueAt(selectedRow, 0);

        workList.deleteSelectedWork(name);

        dispose();
        new AppView(workList);

        IconImage icon = new IconImage(this, "src/main/ui/images/trashIcon.png",
                "Deleted successfully", "Deleted");
        icon.regularIcon();
    }

    /*
     * EFFECTS: implement the seeUnfinishedTask action
     * MODIFIES: this
     * REFERENCE: http://www.javased.com/?post=2604238
     */
    public void filterAction() {
        RowFilter<Object, Object> startsWithAFilter = new RowFilter<Object, Object>() {
            public boolean include(Entry<?, ?> entry) {
                for (int i = entry.getValueCount() - 1; i >= 0; i--) {
                    if (entry.getValue(i).equals(false)) {
                        return true;
                    }
                }
                return false;
            }
        };
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        sorter.setRowFilter(startsWithAFilter);
//        WorkList w = new WorkList();
//        ArrayList<Work> wl = new ArrayList<>();
//        wl = workList.getUnfinishedWork();
//        for (Work next : wl) {
//            w.addWork(next);
//        }
//        dispose();
//        new AppView(w);
    }

    /*
     * EFFECTS: implement the seeAllFinishedTask action
     * MODIFIES: this
     */
    public void showAllAction() {
        dispose();
        new AppView(workList);
    }


    /*
     * EFFECTS: implement the markDoneButton action
     * MODIFIES: this
     */
    public void markDoneAction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            IconImage icon = new IconImage(this, "src/main/ui/images/chooseIcon.png",
                    "Choose a task to mark done", "No Selection");
            icon.regularIcon();
            return;
        }
        String name = (String) model.getValueAt(selectedRow, 0);
        workList.markDone(name);
        dispose();
        new AppView(workList);
        IconImage icon = new IconImage(this, "src/main/ui/images/addIcon.png",
                "Marked successfully", "Marked done");
        icon.regularIcon();
    }



}

