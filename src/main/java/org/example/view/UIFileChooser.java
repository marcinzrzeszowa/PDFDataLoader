package org.example.view;

import javax.swing.*;

public class UIFileChooser extends JFileChooser {
    private static final String DEFAULT_OPEN_DIR = System.getProperty("user.home") + "/Desktop";
    private static UIFileChooser instance = new UIFileChooser(DEFAULT_OPEN_DIR);

    private UIFileChooser(String s ){
    }

    public static UIFileChooser getInstance(){
        return instance;
    }


}
