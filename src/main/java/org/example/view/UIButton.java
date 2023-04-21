package org.example.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UIButton extends JButton {

    public UIButton(String text, int width, int height, ActionListener actionListener) {
        this.setText(text);
        this.addActionListener(actionListener);
       // this.setVerticalAlignment(JButton.CENTER);
        this.setBounds(0,0,width,height);
        this.setFocusable(false);
    }
}
