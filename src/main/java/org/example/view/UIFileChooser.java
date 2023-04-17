package org.example.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIFileChooser extends JFileChooser {
    private static final String DEFAULT_OPEN_DIR = System.getProperty("user.home") + "/Desktop";
    private static UIFileChooser instance = new UIFileChooser(DEFAULT_OPEN_DIR);

    private UIFileChooser(String s ){
        // Dodanie filtrów, aby użytkownik mógł wybierać tylko pliki z rozszerzeniem pdf
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Dokumenty PDF (*.pdf)", "pdf");
        instance.addChoosableFileFilter(filter);

        // Ustawienie domyślnego filtru na filtr dla plików txt
        instance.setFileFilter(instance.getChoosableFileFilters()[0]);
    }

    public static UIFileChooser getInstance(){
        return instance;
    }


}
