package oop.im2020;

import javax.swing.*;
import java.awt.*;

public class ExtractUI extends JPanel {

    private ColourComponent selectedColor = ColourComponent.RED;

    public ExtractUI(){
        super(new BorderLayout());

        final JPanel radioPanel = new JPanel();

        JRadioButton gButton = new JRadioButton("Green");
        radioPanel.add(gButton);
        JRadioButton bButton = new JRadioButton("Blue");
        radioPanel.add(bButton);
        JRadioButton rButton = new JRadioButton("Red");
        radioPanel.add(rButton);
        radioPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        final ButtonGroup bg = new ButtonGroup();
        bg.add(rButton);
        bg.add(gButton);
        bg.add(bButton);
        rButton.setSelected(true);
        rButton.addActionListener((ev) -> colourChosen(ColourComponent.RED));
        gButton.addActionListener((ev) -> colourChosen(ColourComponent.GREEN));
        bButton.addActionListener((ev) -> colourChosen(ColourComponent.BLUE));
        add(radioPanel, BorderLayout.CENTER);
    }
    private void colourChosen(final ColourComponent colour) {
        this.selectedColor = colour;
    }

    public ColourComponent getChosenColor() {
        return this.selectedColor;
    }
}
