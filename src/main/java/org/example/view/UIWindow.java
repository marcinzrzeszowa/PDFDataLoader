package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIWindow extends JFrame implements ActionListener , ViewActionComponent {

    private ViewActionController controller;
    private String filePath, filePathInfo;

    //UI
    private static final int UI_WIDTH = 530;
    private static final int UI_HEIGHT = 400;
    private int HEIGHT = 30;
    private int HEIGHT2 = 20;
    private int WIDTH4 = 100;
    private int WIDTH5 = 300;
    private int WIDTH1 = 200;
    private int WIDTH2 = 200;
    private int WIDTH3 = 500;
    private int XGAP = 1;
    private int YGAP = 1;
    private UIPanel jPTop,jPLeft,jPRight, jPBottom  ;
    private UILabel inputPathLabel, convertInfoLabel, convertFilePathLabel, leftTitleLabel, rightTitleLabel,  spaceLabel;
    private UIRadioButton cmMachineRb, omMachineRb, mMachineRb, excelReportRb, textReportRb, rawTextReportRb;
    private UIButton inputFileButton, convertFileButton;
    private ButtonGroup machinesButtonGroup, reportsButtonGroup;
    private UIMenuBar menuBar;
    UIMenuWindow helpWindow, aboutWindow;


    public UIWindow() {
        //Add Controller to View
        setViewController(new org.example.controller.Controller());

        this.setTitle("Report Converter 1.0");
        this.setSize(UI_WIDTH, UI_HEIGHT);
        this.setResizable(false);
        this.setFont(new Font("Verdana",Font.PLAIN,10));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(XGAP,YGAP));
        this.setLocationRelativeTo(null);
        jPTop = new UIPanel();
        jPLeft = new UIPanel();
        jPRight = new UIPanel();
        jPBottom = new UIPanel();
        jPTop.setPreferredSize(new Dimension(200,80));
        jPLeft.setPreferredSize(new Dimension(UI_WIDTH/2,60));
        jPRight.setPreferredSize(new Dimension(UI_WIDTH/2,60));
        jPBottom.setPreferredSize(new Dimension(200,100));

        Color meniColor = new Color(208, 229, 231);
        jPTop.setBackground(meniColor);
        jPLeft.setBackground(Color.WHITE);
        jPRight.setBackground(Color.WHITE);
        jPBottom.setBackground(meniColor);

        addApplicationICO();

        addMenuBar();

        // TOP LAYOUT

        GridBagLayout jPTopLayout = new GridBagLayout();
        GridBagConstraints gbcTop = new GridBagConstraints();
        jPTop.setLayout(jPTopLayout);

        gbcTop.fill = GridBagConstraints.CENTER;
        gbcTop.gridx = 0;
        gbcTop.gridy = 0;
        inputFileButton = new UIButton("Wybierz plik PDF",WIDTH4,HEIGHT,this);
        jPTop.add(inputFileButton, gbcTop);

        gbcTop.fill = GridBagConstraints.CENTER;
        gbcTop.gridx = 0;
        gbcTop.gridy = 1;
        //gbc.gridwidth = 2; // na 2 wiersze
        inputPathLabel = UILabel.ValueOfUILabel("Lokalizacja", 0,0, WIDTH3, HEIGHT2);
        jPTop.add(inputPathLabel, gbcTop);


        // Left LAYOUT

        GridBagLayout jPTopLeft= new GridBagLayout();
        GridBagConstraints gbcLeft= new GridBagConstraints();
        jPLeft.setLayout(jPTopLeft);

        gbcLeft.fill = GridBagConstraints.BOTH;
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        leftTitleLabel = UILabel.ValueOfVisibleControlUILabel("Wybierz raport z maszyny",0,0, WIDTH3, HEIGHT2,12,true);
        jPLeft.add(leftTitleLabel, gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;
        spaceLabel = UILabel.ValueOfVisibleControlUILabel(" ",0,0, WIDTH3, HEIGHT2,12,true);
        jPLeft.add(spaceLabel, gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        cmMachineRb = new UIRadioButton("Maszyna Współrzędnościowa",1, WIDTH1, HEIGHT, this);
        jPLeft.add(cmMachineRb, gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 3;
        omMachineRb = new UIRadioButton("Maszyna Optyczna",2, WIDTH1, HEIGHT, this);
        jPLeft.add(omMachineRb, gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 4;
        mMachineRb = new UIRadioButton("Mikroskop",3,  WIDTH1, HEIGHT, this);
        jPLeft.add(mMachineRb, gbcLeft);

        machinesButtonGroup = new ButtonGroup();
        machinesButtonGroup.add(cmMachineRb);
        machinesButtonGroup.add(omMachineRb);
        machinesButtonGroup.add(mMachineRb);

        // RIGHT LAYOUT
        GridBagLayout jPTopRight= new GridBagLayout();
        GridBagConstraints gbcRight = new GridBagConstraints();
        jPRight.setLayout(jPTopRight);

        gbcRight.fill = GridBagConstraints.BOTH;
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        rightTitleLabel = UILabel.ValueOfVisibleControlUILabel("Wybierz format pliku po konwersji",0,0, WIDTH3, HEIGHT2,12,true);
        jPRight.add(rightTitleLabel, gbcRight);

        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        spaceLabel = UILabel.ValueOfVisibleControlUILabel(" ",0,0, WIDTH3, HEIGHT2,12,true);
        jPRight.add(spaceLabel, gbcRight);

        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        excelReportRb = new UIRadioButton("Plik MS Excel",1, WIDTH2, HEIGHT, this);
        jPRight.add(excelReportRb, gbcRight);

        gbcRight.gridx = 0;
        gbcRight.gridy = 3;
        textReportRb = new UIRadioButton("Plik tekstowy CVS",2, WIDTH2, HEIGHT, this);
        jPRight.add(textReportRb, gbcRight);

        gbcRight.gridx = 0;
        gbcRight.gridy = 4;
        rawTextReportRb = new UIRadioButton("Surowe dane tekstowe",3, WIDTH2, HEIGHT, this);
        jPRight.add(rawTextReportRb, gbcRight);

        reportsButtonGroup = new ButtonGroup();
        reportsButtonGroup.add(excelReportRb);
        reportsButtonGroup.add(textReportRb);
        reportsButtonGroup.add(rawTextReportRb);

        //Bottom
        GridBagLayout jPTopBottom= new GridBagLayout();
        GridBagConstraints gbcBottom = new GridBagConstraints();
        jPBottom.setLayout(jPTopBottom);

        gbcBottom.fill = GridBagConstraints.CENTER;
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 0;
        convertFileButton = new UIButton("Konwertuj plik ", WIDTH5, HEIGHT,this);
        jPBottom.add(convertFileButton, gbcBottom);

        gbcBottom.gridx = 0;
        gbcBottom.gridy = 1;
        convertInfoLabel = UILabel.ValueOfVisibleControlUILabel("Komunikaty",0,0,  WIDTH3, HEIGHT2, 12, false);
        jPBottom.add(convertInfoLabel, gbcBottom);

        gbcBottom.gridx = 0;
        gbcBottom.gridy = 2;
        convertFilePathLabel = UILabel.ValueOfColorUILabel("Lokalizacja po konwersji",0,0, WIDTH3, HEIGHT2);
        jPBottom.add(convertFilePathLabel, gbcBottom);

        this.add(jPTop, BorderLayout.NORTH);
        this.add(jPLeft, BorderLayout.WEST);
        this.add(jPRight, BorderLayout.EAST);
        this.add(jPBottom, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void addMenuBar() {
        menuBar = new UIMenuBar("Program", this);
        this.setJMenuBar(menuBar);
    }


    @Override
    public void setViewController(ViewActionController controller) {
        this.controller = controller;
    }

    private void addApplicationICO() {
        ImageIcon ico = getResizeImage (newImage("file2.png"),16,16 );
        this.setIconImage(ico.getImage());
    }

    private ImageIcon getResizeImage(ImageIcon imageIcon, int width, int height){
        Image img= imageIcon.getImage();
        Image newImg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == menuBar.getHelpMenuItem()){

           /* String content = "Konwertuj pliki PDF na inne formaty";
            helpWindow = new UIMenuWindow(content, 280, 80);*/
            openHelpFile("m1.txt");
        }

        if(e.getSource() == menuBar.getAboutMenuItem()){
            String content = "<html>" +
                    "Licencja: Report Converter 1.0" +
                    "<br> Dozwolone użycie i rozpowrzechnianie w celach użytkowych, wewnątrz" +
                    "<br> spółki BorgWarner Poland Sp. z o.o." +
                    "<br> Autor: Marcin Janowski.<html>";
            aboutWindow = new UIMenuWindow(content, 580,  130);
        }

        if(e.getSource() == menuBar.getExitMenuItem()){
            System.exit(0);
        }

        if(e.getSource() == inputFileButton){
            String DEFAULT_OPEN_DIR = System.getProperty("user.home") + "/Desktop";
            JFileChooser fileChooser = new JFileChooser(DEFAULT_OPEN_DIR);

            // Dodanie filtrów, aby użytkownik mógł wybierać tylko pliki z rozszerzeniem pdf
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Dokumenty PDF (*.pdf)", "pdf");
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
        boolean conversionSuccessfully;
        if(validateUI()){
            String selectedMachine= machinesButtonGroup.getSelection().getActionCommand();
            String selectedReport = reportsButtonGroup.getSelection().getActionCommand();
            conversionSuccessfully = controller.convertFileAction(filePath,selectedMachine,selectedReport);
            if(conversionSuccessfully){
                showConvertFilePathLabel(filePathInfo);
                showConvertInfoLabel("Plik utworzony w lokalizacji:");
            }else{
                showConvertInfoLabel("Wybierz odpowiedni format pliku, maszynę i rodzaj raportu");
            }
        }else{
            showConvertInfoLabel("Wybierz odpowiedni format pliku, maszynę i rodzaj raportu");
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
       // Path.of(path).toUri();
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
        String absolutePath = getPathFromResources(name);
        return new ImageIcon(absolutePath);
    }

    private static String getPathFromResources(String name) {
        URL res = UIWindow.class.getClassLoader().getResource(name);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    private static void openHelpFile(String name){
        String absolutePath = getPathFromResources(name);
        File file = new File(absolutePath);
        if(!Desktop.isDesktopSupported())
        {
            System.out.println("not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}


