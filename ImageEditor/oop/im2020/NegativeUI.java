package oop.im2020;

import javax.swing.*;

public class NegativeUI extends JPanel {
    public NegativeUI(){
        final JPanel labelPanel = new JPanel();

        labelPanel.add(new JLabel("Change image to negative scale"));
        labelPanel.setBorder(BorderFactory.createTitledBorder(""));
        add(labelPanel);
    }

}
