package org.example.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UIMenuBar extends JMenuBar {
    private JMenu menu;
    private JMenuItem helpMenuItem, aboutMenuItem, exitMenuItem;


    public UIMenuBar(String name, ActionListener actionListener) {
        menu = new JMenu(name);

        helpMenuItem = new JMenuItem("Pomoc");
        aboutMenuItem = new JMenuItem("Licencja");
        exitMenuItem = new JMenuItem("Zamknij");

        helpMenuItem.addActionListener(actionListener);
        aboutMenuItem.addActionListener(actionListener);
        exitMenuItem.addActionListener(actionListener);

        exitMenuItem.setMnemonic(KeyEvent.VK_Q);

        menu.add(helpMenuItem);
        menu.add(aboutMenuItem);
        menu.add(exitMenuItem);
        this.add(menu);
    }

    public JMenuItem getHelpMenuItem() {
        return helpMenuItem;
    }

    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

}
