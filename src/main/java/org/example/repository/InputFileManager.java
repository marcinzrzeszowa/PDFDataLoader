package org.example.repository;
import org.example.service.MachineService;
import org.example.report.ReportCharacteristic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputFileManager extends FileManager{
    private static InputFileManager instance = new InputFileManager();

    private InputFileManager(){
    }

    public static InputFileManager getInstance(){
        return instance;
    }

    public List<ReportCharacteristic> createCharacteristics(String filePath, MachineService machine){


        //Return parsedFileFragment = parseFileFragment(filePath, startPage, endPage);
        StringBuilder rawFileText = parseFile(filePath);
        StringBuilder characteristicsText = extractCharacteristicTextLines(rawFileText , machine.getBeginReportValuesRegex(), machine.getFinishReportValuesRegex());

        Map<String, List<Double>> characteristicMap = new HashMap<>();
        ReportCharacteristic characteristic;
        String lineOfText, characteristicName;
        List<ReportCharacteristic> characteristicsList = new ArrayList<>();
        BufferedReader bufReader = new BufferedReader(new StringReader(rawFileText.toString()));

        while(true)
        {
            try {
                if (!((lineOfText=bufReader.readLine()) != null)) break;
                //Find characteristic name in text line
                characteristicName = findCharacteristicByRegex(machine.getCharacteristicNameRegex(), lineOfText);
                if(!characteristicName.equals("")){
                    //Extract values from text by characteristic name regex. Returns it as map of double values
                    characteristicMap = readCharacteristicLine(machine.getCharacteristicNameRegex(),lineOfText);
                    characteristic = createCharacteristics(characteristicMap);
                    characteristicsList.add(characteristic);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        characteristicsList = characteristicsList.stream()
                .filter(e->e!=null).toList();
        return characteristicsList;
    }

    private StringBuilder extractCharacteristicTextLines(StringBuilder rawFileText, String beginReportValuesRegex, String finishReportValuesRegex) {
        System.out.println();
        return null;
    }

    private String findCharacteristicByRegex(String validator, String lineOfText){
        Pattern pattern1 = Pattern.compile(validator);
        Matcher matcher1 = pattern1.matcher(lineOfText);
        String name="";
        while(matcher1.find())
        {
            name =(matcher1.group());
        }
        return name;
    }

    private Map<String,List<Double>> readCharacteristicLine(String validator, String lineOfText){
        Pattern pattern1 = Pattern.compile(validator);
        Matcher matcher1 = pattern1.matcher(lineOfText);
        String name = "";
        List<Double> preparedValues = null;
        Map<String,List<Double>> matchedTextLine = new HashMap<>();
        while(matcher1.find())
        {
            name =(matcher1.group());
            preparedValues = prepareValues(lineOfText);
            matchedTextLine.put(name,preparedValues);
        }
        return matchedTextLine;
    }

    public static ReportCharacteristic createCharacteristics(Map<String,List<Double>>dataMap) {
        final int ACTUAL_VALUE_INDEX = 0;
        final int NOMINAL_VALUE_INDEX = 1;
        final int DIFFERENCE_VALUE_INDEX = 2;

       ReportCharacteristic characteristic = new ReportCharacteristic();
        for(String k: dataMap.keySet()){
            characteristic = new ReportCharacteristic( k,
                    dataMap.get(k).get(ACTUAL_VALUE_INDEX),
                    dataMap.get(k).get(NOMINAL_VALUE_INDEX),
                    dataMap.get(k).get(ACTUAL_VALUE_INDEX) - dataMap.get(k).get(NOMINAL_VALUE_INDEX));
        }
        return characteristic ;
    }

    //Change text characteristics values in to Double values
    private List<Double> prepareValues(String lineOfText) {
        final int NUMBER_OF_VALUES_FOR_CHARACTERISTIC = 3;
        final String VALUES_SEPARATOR = "\s+";
        List<String> strSplit = Arrays.asList(lineOfText.split(VALUES_SEPARATOR));

        List<Double> parsedVList = strSplit
                .stream()
                .filter(e->isNumeric(e))
                .map(e->Double.valueOf(e))
                .map(e->numberParser(e))
                .limit(NUMBER_OF_VALUES_FOR_CHARACTERISTIC)
                .collect(Collectors.toList());
        return parsedVList;
    }

    private static Double numberParser(Double inputNr) {
        NumberFormat formatter = new DecimalFormat("0.000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        String strNr = formatter.format(inputNr);
        strNr = strNr.replaceAll(",",".");
        Double nr = Double.parseDouble(strNr);
        return nr;
    }


}
