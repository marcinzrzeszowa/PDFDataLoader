package org.example.view;

import org.example.controller.ActionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIWindow extends JFrame implements ActionListener {

    private static final int UI_WIDTH = 580;
    private static final int UI_HEIGHT = 400;
    private int X1 = 50;
    private int XGAP = 250;
    private int X2 = X1 + XGAP;
    private int HEIGHT = 30;
    private int HEIGHT2 = 20;
    private int WIDTH4 = 100;
    private int WIDTH1 = 200;
    private int WIDTH2 = 200;
    private int WIDTH3 = 500;
    private int X_CENTER = UI_WIDTH /2 - WIDTH4/2;

    private int YGAP = 40;
    private int Y0 = 0;
    private int Y1 = Y0 + YGAP;
    private int Y2 = Y1 + YGAP;
    private int Y3 = Y2 + YGAP;
    private int Y4 = Y3 + YGAP;
    private int Y5 = Y4 + YGAP;
    private int Y6 = Y5 + YGAP;
    private int Y7 = Y6 + YGAP;
    private int Y8 = Y7 + 20;


    private UIPanel panel;
    private UILabel inputPathLabel, convertInfoLabel, convertFilePathLabel, machineSelectLabel, reportSelectLabel , spaceLabel;
    private UIRadioButton cmMachineRb, omMachineRb, mMachineRb, excelReportRb, textReportRb, rawTextReportRb;
    private UIButton inputFileButton, convertFileButton;

    private ButtonGroup machinesButtonGroup, reportsButtonGroup;

    private ViewActionController actionController;

    private String filePath, filePathInfo;


    public UIWindow() {
        actionController = new ActionController();
        panel = new UIPanel(UI_WIDTH, UI_HEIGHT);

        this.setLayout(null);
        //this.getContentPane().setBackground(Color.darkGray);
        this.setLocationRelativeTo(null);
        this.setTitle("Konwertuj raport PDF");
        this.setSize(UI_WIDTH, UI_HEIGHT);
        this.setResizable(false);
        this.setFont(new Font("Verdana",Font.PLAIN,10));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Buttons
        inputFileButton = new UIButton("Wybierz plik PDF",X_CENTER,Y1,WIDTH4,HEIGHT,this);
        panel.add(inputFileButton);
        convertFileButton = new UIButton("Konwertuj", X_CENTER, Y6, WIDTH4, HEIGHT,this);
        this.add(convertFileButton);

        // Labels
       /* spaceLabel = new UILabel( "", X1,Y1, WIDTH3, HEIGHT2, 12, true);
        panel.add(spaceLabel);*/

        /*machineSelectLabel = new UILabel("Raport z maszyny", X1,Y1, WIDTH1, HEIGHT2, 12, true);
        panel.add(machineSelectLabel);
        reportSelectLabel = new UILabel("Zamień na raport", X2,Y1, WIDTH1, HEIGHT2, 12, true);
        panel.add(reportSelectLabel);*/
        inputPathLabel = new UILabel("Lokalizacja", X1,Y2, WIDTH3, HEIGHT2);
        panel.add(inputPathLabel);
        convertFilePathLabel = new UILabel("Lokalizacja po konwersji",X1,Y8, WIDTH3, HEIGHT2);
        this.add(convertFilePathLabel);
        convertInfoLabel = new UILabel("Komunikaty", X1,Y7, WIDTH3, HEIGHT2, true);
        this.add(convertInfoLabel);

        //Radio Buttons
        machinesButtonGroup = new ButtonGroup();
        cmMachineRb = new UIRadioButton("Maszyna Współrzędnościowa",1, X1, Y3, WIDTH1, HEIGHT, this);
        machinesButtonGroup.add(cmMachineRb);
        this.add(cmMachineRb);
        omMachineRb = new UIRadioButton("Maszyna Optyczna",2, X1, Y4, WIDTH1, HEIGHT, this);
        machinesButtonGroup.add(omMachineRb);
        this.add(omMachineRb);
        mMachineRb = new UIRadioButton("Mikroskop",3, X1, Y5, WIDTH1, HEIGHT, this);
        machinesButtonGroup.add(mMachineRb);
        //add(mMachineRb);

        reportsButtonGroup = new ButtonGroup();
        excelReportRb = new UIRadioButton("Plik MS Excel",1, X2, Y3, WIDTH2, HEIGHT, this);
        reportsButtonGroup.add(excelReportRb);
        this.add(excelReportRb);
        textReportRb = new UIRadioButton("Plik tekstowy CVS",2,X2, Y4, WIDTH2, HEIGHT, this);
        reportsButtonGroup.add(textReportRb);
        this.add(textReportRb);
        rawTextReportRb = new UIRadioButton("Surowe dane tekstowe",3, X2, Y5, WIDTH2, HEIGHT, this);
        reportsButtonGroup.add(rawTextReportRb);
        this.add(rawTextReportRb);

        addApplicationICO();


        this.add(panel);
        this.setVisible(true);
    }

    private void addApplicationICO() {
        ImageIcon ico = newImage("cc1.jpg");
        this.setIconImage(ico.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == inputFileButton){
            String DEFAULT_OPEN_DIR = System.getProperty("user.home") + "/Desktop";

            JFileChooser fileChooser = new JFileChooser(DEFAULT_OPEN_DIR);

            // Dodanie filtrów, aby użytkownik mógł wybierać tylko pliki z rozszerzeniem txt i pdf
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
            fileChooser.addChoosableFileFilter(filter);
            filter = new FileNameExtensionFilter("Dokumenty PDF (*.pdf)", "pdf");
            fileChooser.addChoosableFileFilter(filter);

            // Ustawienie domyślnego filtru na filtr dla plików txt
            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String pathStr = fileChooser.getSelectedFile().getAbsolutePath();
                    String dirPath = fileChooser.getCurrentDirectory().toString();
                    this.filePath = pathStr;
                    this.filePathInfo = dirPath;
                    showInputPathLabel(pathStr);
                    hideConvertInfoLabel();
                    hideConvertInfoLabel();
              }
        }

        if(e.getSource() == convertFileButton){
            convertActionEvent();
        }
        repaint();
    }

    private void convertActionEvent() {
        if(validateUI()){
            String selectedMachine= machinesButtonGroup.getSelection().getActionCommand();
            String selectedReport = reportsButtonGroup.getSelection().getActionCommand();
            actionController.convertFileAction(filePath,selectedMachine,selectedReport);
            showConvertFilePathLabel(filePathInfo);
            showConvertInfoLabel("Plik utworzony w lokalizacji:");
        }else{
            showConvertInfoLabel("Wybierz plik, maszynę i rodzaj raportu");
        }
    }

    private boolean validateUI() {
        if((cmMachineRb.isSelected() || omMachineRb.isSelected() || mMachineRb.isSelected())
                && (excelReportRb.isSelected() || textReportRb.isSelected() || rawTextReportRb.isSelected())){
                 return true;
        }
         return false;
    }


    private void showInputPathLabel(String path) {
        inputPathLabel.setText(path);
        inputPathLabel.setVisible(true);
    }
    private void hideInputPathLabel() {
        inputPathLabel.setVisible(false);
    }
    private void showConvertFilePathLabel(String path) {
        Path.of(path).toUri();
        convertFilePathLabel.setText(path);
        convertFilePathLabel.setVisible(true);
    }
    private void hideConvertFilePathLabel() {
        convertFilePathLabel.setVisible(false);
    }
    private void showConvertInfoLabel(String text) {
        convertInfoLabel.setText(text);
        convertInfoLabel.setVisible(true);
    }
    private void hideConvertInfoLabel() {
        convertInfoLabel.setVisible(false);
    }


    private static ImageIcon newImage(String name){
        URL res = UIWindow.class.getClassLoader().getResource(name);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String absolutePath = file.getAbsolutePath();
        return new ImageIcon(absolutePath);
    }
}
