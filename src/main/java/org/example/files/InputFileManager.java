package org.example.files;
import org.example.machine.CharacteristicValidator;
import org.example.machine.MachineReport;
import org.example.report.ReportCharacteristic;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputFileManager extends FileManager{
    private PDFFile pdfFile;

    private static InputFileManager instance = new InputFileManager(new PDFFile());

    private InputFileManager(PDFFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public static InputFileManager getInstance(){
        return instance;
    }

    private StringBuilder parseFile(String filePath){
        StringBuilder parsedText = new StringBuilder();
        if(isEditable(filePath)) {
            parsedText = pdfFile.parseFile(filePath);
        }
        return parsedText;
    }

    public List<ReportCharacteristic> createCharacteristic(String filePath, MachineReport machine){
        List<ReportCharacteristic> reportCharacteristicList = null;
        StringBuilder inputText  = parseFile(filePath);
            try {
                reportCharacteristicList = machine.extractReportCharacteristics(inputText, this);
                //TODO dodac 2 metody z impl jak strategia
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return reportCharacteristicList;
    }

    public String findCharacteristicByRegex(CharacteristicValidator validator, String lineOfText){
        Pattern pattern1 = Pattern.compile(validator.getNameRegex());
        Matcher matcher1 = pattern1.matcher(lineOfText);
        String name="";
        while(matcher1.find())
        {
            name =(matcher1.group());
        }
        return name;
    }

    public Map<String,List<Double>> readCharacteristicLine(CharacteristicValidator validator, String lineOfText){
        Pattern pattern1 = Pattern.compile(validator.getNameRegex());
        Matcher matcher1 = pattern1.matcher(lineOfText);
        String name;
        List<Double> preparedValues;
        Map<String,List<Double>> matchedTextLine = new HashMap<>();
        while(matcher1.find())
        {
            name =(matcher1.group());
            preparedValues = prepareValues(lineOfText);
            matchedTextLine.put(name,preparedValues);
        }
        return matchedTextLine;
    }

    public static ReportCharacteristic createCharacteristic(Map<String,List<Double>>dataMap) {
        final int ACTUAL_VALUE_INDEX = 0;
        final int NOMINAL_VALUE_INDEX = 1;
        final int DIFFERENCE_VALUE_INDEX = 2;

       ReportCharacteristic characteristic = new ReportCharacteristic();
        for(String k: dataMap.keySet()){
            characteristic = new ReportCharacteristic( k,
                    dataMap.get(k).get(ACTUAL_VALUE_INDEX),
                    dataMap.get(k).get(NOMINAL_VALUE_INDEX),
                    dataMap.get(k).get(DIFFERENCE_VALUE_INDEX));
        }
        return characteristic ;
    }

    //Change String values to Double
    private List<Double> prepareValues(String lineOfText) {

        //TODO format 0,00
        final DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.UP);

        final int NUMBER_OF_VALUES_FOR_CHARACTERISTIC = 3;
        final String VALUES_SEPARATOR = "\s+";

        lineOfText = lineOfText.replace(",",".");
        List<String> strSplit = Arrays.asList(lineOfText.split(VALUES_SEPARATOR));


        List<Double> parsedVList = strSplit.stream()
                .filter(e->isNumeric(e))
                .map(e->Double.valueOf(e))
                .limit(NUMBER_OF_VALUES_FOR_CHARACTERISTIC)
                .collect(Collectors.toList());
        return parsedVList;
    }
}
