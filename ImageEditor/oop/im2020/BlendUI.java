package oop.im2020;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BlendUI extends JPanel {
    private final JTextField otherImagePath = new JTextField(60);
    private final JButton fileChooserButton = new JButton("Open");
    private final JSlider alphaSlider = new JSlider(0, 100);


    private final JFileChooser chooser = new JFileChooser();
    private File file;

    public BlendUI(){
        super(new BorderLayout());
        this.chooser.setMultiSelectionEnabled(false);
        this.chooser.setCurrentDirectory(new File("."));
        final JPanel pathPanel = new JPanel();
        pathPanel.add(this.otherImagePath);
        pathPanel.add(this.fileChooserButton);
        pathPanel.setBorder(BorderFactory.createTitledBorder("Image to difference"));
        alphaSlider.setBorder(BorderFactory.createTitledBorder("How close to match the colour to blend through"));

        add(pathPanel, BorderLayout.NORTH);
        add(this.alphaSlider, BorderLayout.SOUTH);

        this.otherImagePath.setEditable(false);
        this.fileChooserButton.addActionListener(ev -> showChooser());
    }
    private void showChooser() {
        if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.file = this.chooser.getSelectedFile();
            this.otherImagePath.setText(this.file.getPath());
        }
    }
    public double getSensitivity() {
        return this.alphaSlider.getValue() / 100.0;
    }

    public File getOtherImagePath() {
        return this.file;
    }
}
