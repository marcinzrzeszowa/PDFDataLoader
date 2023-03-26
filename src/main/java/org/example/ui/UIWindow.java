package org.example.ui;

import org.example.machine.MachineReport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private static int X = 50;
    private static int X_GAP = 20;
    private static int Y = 20;
    private static int Y_GAP = 20;
    private static int C_WIDTH = 100;
    private static int C_HEIGHT = 30;

    private JLabel inputPathLabel, convertPathLabel, convertInfoLabel;
    private JButton inputFileButton, convertFileB;
    private JRadioButton cmMachineRb, omMachineRb, mMachineRb, excelReportRb, textReportRb, rawTextReportRb;

    private MachineReport machineReport;


    public UIWindow() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0,0,WIDTH,HEIGHT);

        this.getContentPane().setBackground(Color.darkGray);
        this.setLocationRelativeTo(null);
        this.setTitle("Zamień plik PDF na inny format");
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        inputFileButton = new JButton("Wybierz plik PDF");
        inputFileButton.addActionListener(this);
        inputFileButton.setFocusable(false);
        inputFileButton.setBounds(20,0,100,30);
        panel.add(inputFileButton);

        inputPathLabel = new JLabel();
        inputPathLabel.setBounds(50,200,100,30);
        inputPathLabel.setText("Komunikat");
        inputPathLabel.setVisible(false);
        panel.add(inputPathLabel);




        ButtonGroup machinesButtonGroup = new ButtonGroup();
        // Tworzenie kontrolki radio button 1
        cmMachineRb = new JRadioButton("CMM");
        cmMachineRb.setBounds(20, 70, 60, 30);
        cmMachineRb.addActionListener(this); // dodanie ActionListenera
        machinesButtonGroup.add(cmMachineRb);
        add(cmMachineRb);

        // Tworzenie kontrolki radio button 2
        omMachineRb = new JRadioButton("OPTIC");
        omMachineRb.setBounds(20, 120, 60, 30);
        omMachineRb.addActionListener(this); // dodanie ActionListenera
        machinesButtonGroup.add(omMachineRb);
        add(omMachineRb);

        // Tworzenie kontrolki radio button 3
        mMachineRb = new JRadioButton("MICROSCOPE");
        mMachineRb.setBounds(20, 170, 60, 30);
        mMachineRb.addActionListener(this); // dodanie ActionListenera
        machinesButtonGroup.add(mMachineRb);
        //add(mMachineRb);




        ButtonGroup buttonGroup = new ButtonGroup();
        // Tworzenie kontrolki radio button 1
        excelReportRb = new JRadioButton("Raport Excel");
        excelReportRb.setBounds(120, 70, 200, 30);
        excelReportRb.addActionListener(this); // dodanie ActionListenera
        buttonGroup.add(excelReportRb);
        add(excelReportRb);

        // Tworzenie kontrolki radio button 2
        textReportRb = new JRadioButton("Raport Tekstowy CVS");
        textReportRb.setBounds(120, 120, 200, 30);
        textReportRb.addActionListener(this); // dodanie ActionListenera
        buttonGroup.add(textReportRb);
        add(textReportRb);

        // Tworzenie kontrolki radio button 3
        rawTextReportRb = new JRadioButton("Surowe Dane tekstowe");
        rawTextReportRb.setBounds(120, 170, 200, 30);
        rawTextReportRb.addActionListener(this); // dodanie ActionListenera
        buttonGroup.add(rawTextReportRb);
        add(rawTextReportRb);

        convertFileB = new JButton("Konwertuj");
        convertFileB.setBounds(250, 220, 100, 30);
        convertFileB.addActionListener(this); // dodanie ActionListenera
        add(convertFileB);

        //JLabel = do wyswietlania tekstu i grafiki. Etykieta do czytania bez iteracji


        convertInfoLabel = new JLabel();
        convertInfoLabel.setText("Info Label");
        //convertInfoLabel.setForeground(new Color(234,222,223));
        convertInfoLabel.setFont(new Font("Verdana",Font.PLAIN,20)); // Font
        convertInfoLabel.setOpaque(true); // wlancza tlo
        convertInfoLabel.setBounds(20,250, 400, 30); // pozycja labela
        this.add(convertInfoLabel);

        convertPathLabel = new JLabel();
        convertPathLabel.setText("Convert result Label path");

        convertPathLabel.setFont(new Font("Verdana",Font.PLAIN,20)); // Font
        convertPathLabel.setOpaque(true); // wlancza tlo
        convertPathLabel.setBounds(20,300, 400, 30); // pozycja labela
        this.add(convertPathLabel);


        ImageIcon ico = newImage("logo100.png");
        this.setIconImage(ico.getImage());

        this.setLayout(null);


        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inputFileButton){
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
            // Dodanie filtrów, aby użytkownik mógł wybierać tylko pliki z rozszerzeniem txt i pdf
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
            fileChooser.addChoosableFileFilter(filter);
            filter = new FileNameExtensionFilter("Dokumenty PDF (*.pdf)", "pdf");
            fileChooser.addChoosableFileFilter(filter);

            // Ustawienie domyślnego filtru na filtr dla plików txt
            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                inputPathLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
                inputPathLabel.setVisible(true);
                repaint();
              }
        }

        if (e.getSource() == excelReportRb) {
            System.out.println("Radio Button 1 został wybrany");
        } else if (e.getSource() == textReportRb) {
            System.out.println("Radio Button 2 został wybrany");
        } else if (e.getSource() == rawTextReportRb) {
            System.out.println("Radio Button 3 został wybrany");
        }

        if(e.getSource() == convertFileB){
            System.out.println("Convert");
        }
    }

    static ImageIcon newImage(String name){
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
