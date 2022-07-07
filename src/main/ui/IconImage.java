package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Create resized iconImage for JDialog
public class IconImage {
    private final String fileName;
    private final String messageName;
    private final String titleName;
    private final JFrame parent;

    /*
     * EFFECTS: create a IconImage which can use two methods
     */
    public IconImage(JFrame parent,String fileName, String messageName, String titleName) {
        this.fileName = fileName;
        this.messageName = messageName;
        this.titleName = titleName;
        this.parent = parent;

    }

    /*
     * EFFECTS: create a JDialog for message display with passed in image file location, messageName and titleName
     * MODIFIES: this
     */
    public void regularIcon() {
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = 100;
        int height = 100;
        if (icon != null) {
            icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        }
        JOptionPane.showMessageDialog(parent, messageName,
                titleName, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    /*
     * EFFECTS: create a JDialog for confirmation with passed in image file location, messageName and titleName
     * MODIFIES: this
     */
    public int optionIcon() {
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = 100;
        int height = 100;
        if (icon != null) {
            icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        }
        return JOptionPane.showConfirmDialog(parent, messageName,
                titleName, JOptionPane.YES_NO_OPTION,  JOptionPane.QUESTION_MESSAGE, icon);
    }

}
