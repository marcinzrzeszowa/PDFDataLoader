package org.example.view;

import javax.swing.*;
import java.awt.*;

public class UIMenuWindow {
        private UIPanel panel;
        private UILabel text;
        private JFrame frame;


    public UIMenuWindow(String content, Integer width, Integer height) {

        frame = new JFrame();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setFont(new Font("Verdana",Font.PLAIN,10));
        frame.setLocationRelativeTo(null);

        GridBagLayout layout= new GridBagLayout();
        GridBagConstraints layoutPosition = new GridBagConstraints();
        layoutPosition.fill = GridBagConstraints.BOTH;
        layoutPosition.gridx = 0;
        layoutPosition.gridy = 0;
        frame.setLayout(layout);




        panel = new UIPanel();
        panel.setPreferredSize(new Dimension(width,height));
        GridBagLayout panelLayout = new GridBagLayout();
        GridBagConstraints position = new GridBagConstraints();
        panel.setLayout(panelLayout);


        text = UILabel.ValueOfColorUILabel(content,0,0,width,height);
        position.gridx =0;
        position.gridy= 0;
        text.setVisible(true);
        panel.add(text, position);


        frame.add(panel,layoutPosition);
        frame.setVisible(true);
    }
}
