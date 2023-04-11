package org.example.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UIButton extends JButton {

    public UIButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        this.setText(text);
        this.addActionListener(actionListener);
       // this.setVerticalAlignment(JButton.CENTER);
        this.setBounds(x,y,width,height);
        this.setFocusable(false);
    }
}
