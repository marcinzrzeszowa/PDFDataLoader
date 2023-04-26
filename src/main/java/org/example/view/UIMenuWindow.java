package org.example.view;

import javax.swing.*;
import java.awt.*;

public class UIMenuWindow {
        private Integer HEIGHT, WIDTH;
        private UIPanel panel;
        private UILabel text;
        private JFrame frame;


    public UIMenuWindow(String content, Integer width, Integer height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setFont(new Font("Verdana",Font.PLAIN,10));
        frame.setLocationRelativeTo(null);
        FlowLayout frameLayout = new FlowLayout(10);
        frame.setLayout(frameLayout);

        panel = new UIPanel();

        text = UILabel.ValueOfColorUILabel(content,0,0,WIDTH,HEIGHT);
        text.setVisible(true);
        panel.add(text);

        frame.add(panel);
        frame.setVisible(true);
    }
}
