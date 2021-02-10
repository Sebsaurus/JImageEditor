/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author childm
 */
public class GrayscaleUI extends JPanel {

    public GrayscaleUI() {
        super(new BorderLayout());

        final JPanel pathPanel = new JPanel();
        pathPanel.add(new JLabel("Change image to gray scale"),BorderLayout.NORTH);
        pathPanel.setBorder(BorderFactory.createTitledBorder(""));
        this.setMinimumSize(new Dimension(300,300));
        add(pathPanel, BorderLayout.NORTH);

    }


}
