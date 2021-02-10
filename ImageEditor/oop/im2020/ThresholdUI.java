package oop.im2020;

import javax.swing.*;
import java.awt.*;

public class ThresholdUI extends JPanel{
    JTextField textField = new JTextField(20);

    public ThresholdUI(){
        super(new BorderLayout());

        final JPanel pathPanel = new JPanel();
        pathPanel.add(new JLabel(""),BorderLayout.NORTH);
        pathPanel.add(textField, BorderLayout.CENTER);
        pathPanel.setBorder(BorderFactory.createTitledBorder("Change image to Threshold"));
        add(pathPanel, BorderLayout.NORTH);
    }
    public int ThresholdNumber(){
        return Integer.parseInt(textField.getText());
    }
}
