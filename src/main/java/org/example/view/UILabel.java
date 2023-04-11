package org.example.view;

import javax.swing.*;
import java.awt.*;

public class UILabel extends JLabel{
    //JLabel = do wyswietlania tekstu i grafiki. Etykieta do czytania bez iteracji

    public UILabel(String text, int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        setFont(new Font("Verdana",Font.PLAIN,10));
        this.setOpaque(false);
        this.setText(text);
        this.setVisible(false);

        //convertInfoLabel.setForeground(new Color(234,222,223));
        //convertInfoLabel.setOpaque(true); // wlancza tlo
    }

    public UILabel(String text, int x, int y, int width, int height, boolean isRed) {
        this.setBounds(x, y, width, height);
        setFont(new Font("Verdana", Font.BOLD, 12));
        this.setOpaque(false);
        this.setText(text);
        this.setVisible(false);
        if (isRed) {
            this.setForeground(Color.RED);
        }
    }

    public UILabel(String text, int x, int y, int width, int height, int fontSize, boolean visibility) {
        this.setBounds(x, y, width, height);
        setFont(new Font("Verdana", Font.BOLD, fontSize));
        this.setOpaque(false);
        this.setText(text);
        this.setVisible(visibility);

    }
}
