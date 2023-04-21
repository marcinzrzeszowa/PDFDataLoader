package org.example.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UIRadioButton extends JRadioButton {
    private int option;


    public UIRadioButton(String text, int optionNr, int width, int height, ActionListener actionListener) {
        this.option = optionNr;
        this.setActionCommand(String.valueOf(optionNr));
        this.setText(text);
        this.setBounds(0, 0, width, height);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addActionListener(actionListener);
    }

    public int getOption() {
        return option;
    }
}
