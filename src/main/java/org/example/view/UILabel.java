package org.example.view;

import javax.swing.*;
import java.awt.*;

public class UILabel extends JLabel{
    //JLabel = do wyswietlania tekstu i grafiki. Etykieta do czytania bez iteracji

    private UILabel(String text, int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        setFont(new Font("Verdana",Font.PLAIN,10));
        this.setOpaque(false);
        this.setText(text);
        this.setVisible(false);
        //convertInfoLabel.setForeground(new Color(234,222,223));
        //convertInfoLabel.setOpaque(true); // wlancza tlo
    }
    public static UILabel ValueOfUILabel(String text, int x, int y, int width, int height){
        return new UILabel (text, x, y, width, height);
    }

    private UILabel(String text, int x, int y, int width, int height, boolean isRed) {
        this.setBounds(x, y, width, height);
        setFont(new Font("Verdana", Font.BOLD, 12));
        this.setOpaque(false);
        this.setText(text);
        this.setVisible(false);
        if (isRed) {
            this.setForeground(new Color(0, 93, 225));
        }
    }

    public static UILabel ValueOfColorUILabel(String text, int x, int y, int width, int height){
        return new UILabel (text, x, y, width, height, true);
    }

    private UILabel(String text, int x, int y, int width, int height, int fontSize, boolean visibility) {
        this.setBounds(x, y, width, height);
        setFont(new Font("Verdana", Font.BOLD, fontSize));
        this.setOpaque(false);
        this.setText(text);
        this.setVisible(visibility);
    }

    public static UILabel ValueOfVisibleControlUILabel(String text, int x, int y, int width, int height, int fontSize, boolean visibility){
        return new UILabel (text, x,  y, width, height, fontSize, visibility);
    }

}
