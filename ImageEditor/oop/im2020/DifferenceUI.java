package oop.im2020;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DifferenceUI extends JPanel {

    private final JTextField otherImagePath = new JTextField(60);

    private final JFileChooser chooser = new JFileChooser();
    private File file;

    public DifferenceUI(){
        super(new BorderLayout());
        this.chooser.setMultiSelectionEnabled(false);
        this.chooser.setCurrentDirectory(new File("."));

        final JPanel pathPanel = new JPanel();
        pathPanel.add(this.otherImagePath);
        JButton fileChooserButton = new JButton("Open");
        pathPanel.add(fileChooserButton);
        pathPanel.setBorder(BorderFactory.createTitledBorder("Image to difference"));

        add(pathPanel, BorderLayout.NORTH);

        this.otherImagePath.setEditable(false);
        fileChooserButton.addActionListener(ev -> showChooser());
    }
    private void showChooser() {
        if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.file = this.chooser.getSelectedFile();
            this.otherImagePath.setText(this.file.getPath());
        }
    }

    public File getOtherImagePath() {
        return this.file;
    }
}
