/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.*;

import javax.swing.*;

/**
 *
 * @author childm
 */
public class TintUI extends JPanel {

    private final JSlider alphaSlider = new JSlider(0, 100);
    private ColourComponent selectedColor = ColourComponent.RED;

    public TintUI() {
        super(new BorderLayout());


        final JPanel radioPanel = new JPanel();
        JRadioButton rButton = new JRadioButton("Red");
        radioPanel.add(rButton);
        JRadioButton gButton = new JRadioButton("Green");
        radioPanel.add(gButton);
        JRadioButton bButton = new JRadioButton("Blue");
        radioPanel.add(bButton);
        radioPanel.setBorder(BorderFactory.createTitledBorder("Colors"));


        // make radio buttons mutually exclusive
        final ButtonGroup bg = new ButtonGroup();
        bg.add(rButton);
        bg.add(gButton);
        bg.add(bButton);


        rButton.setSelected(true);


        rButton.addActionListener((ev) -> colourChosen(ColourComponent.RED));
        gButton.addActionListener((ev) -> colourChosen(ColourComponent.GREEN));
        bButton.addActionListener((ev) -> colourChosen(ColourComponent.BLUE));

        final JPanel sliderPanel = new JPanel();
        sliderPanel.add(alphaSlider);
        sliderPanel.setBorder(BorderFactory.createTitledBorder("Slider"));

        add(radioPanel, BorderLayout.CENTER);
        add(sliderPanel, BorderLayout.SOUTH);
    }

    private void colourChosen(final ColourComponent colour) {
        this.selectedColor = colour;
    }

    public ColourComponent getChosenColor() {
        return this.selectedColor;
    }

    public int getAlpha() {
        return this.alphaSlider.getValue();
    }
}
