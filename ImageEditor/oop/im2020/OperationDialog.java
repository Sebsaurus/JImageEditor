/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author childm
 */
public class OperationDialog extends JDialog {

    private boolean wasCancelled = true;
    
    public OperationDialog(ImageProcessor frame, JPanel optionPanel) {
        super(frame, true);
      
        JPanel buttonPanel = new JPanel();
        JButton applyButton = new JButton("Apply");
        buttonPanel.add(applyButton);
        buttonPanel.setAlignmentY(1.0f);
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        add(optionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        applyButton.addActionListener(ev -> doApplyButton());
        cancelButton.addActionListener(ev -> setVisible(false));

        pack();
    }

    public boolean wasCancelled() {
    	return this.wasCancelled;
    }
    
    private void doApplyButton() {
        setVisible(false);
        this.wasCancelled = false;
    }
}
