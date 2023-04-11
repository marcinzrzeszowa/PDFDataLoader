package org.example.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UIRadioButton extends JRadioButton {
    private int option;


    public UIRadioButton(String text, int optionNr, int x, int y, int width, int height, ActionListener actionListener) {
        this.option = optionNr;
        this.setActionCommand(String.valueOf(optionNr));
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.addActionListener(actionListener);
    }

    public int getOption() {
        return option;
    }
}
