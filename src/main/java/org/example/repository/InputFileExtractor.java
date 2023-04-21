package org.example.repository;
import org.example.machineservice.Machine;
import org.example.reportservice.ReportCharacteristic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputFileExtractor extends FileExtractor {
    private static InputFileExtractor instance = new InputFileExtractor();

    private InputFileExtractor(){
    }

    public static InputFileExtractor getInstance(){
        return instance;
    }

    public List<ReportCharacteristic> createCharacteristics(String filePath, Machine machine){

        //Parse file to text lines
        StringBuilder rawFileText = parseFile(filePath);

        //Cut text just with characteristic values
        String characteristicsText = extractCharacteristicTextLine(rawFileText, machine);
        System.out.println(characteristicsText);

        Map<String, List<Double>> characteristicMap = new HashMap<>();
        ReportCharacteristic characteristic;
        String lineOfText, characteristicName;
        List<ReportCharacteristic> characteristicsList = new ArrayList<>();
        BufferedReader bufReader = new BufferedReader(new StringReader(characteristicsText));

        while(true)
        {
            try {
                if (!((lineOfText=bufReader.readLine()) != null)) break;
                characteristicName = findCharacteristicByRegex(machine, lineOfText);
                if(!characteristicName.equals("")){
                    //Just info
                    System.out.print(characteristicName + " ");

                    characteristicMap = readCharacteristicLine(machine,lineOfText);
                    characteristic = createCharacteristics(characteristicMap);
                    characteristicsList.add(characteristic);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /*characteristicsList = characteristicsList.stream()
                .filter(e->e!=null).toList();*/
        return characteristicsList;
    }

    private String extractCharacteristicTextLine(StringBuilder rawFileText, Machine machine) {
        int startIndex = rawFileText.indexOf(machine.getBeginReportCutRegex());
        int endIndex = rawFileText.lastIndexOf(machine.getFinishReportCutRegex());
        String result = rawFileText.substring(startIndex,endIndex);
        return result;
    }

    //Find characteristic name in text line
    private String findCharacteristicByRegex(Machine machine, String lineOfText){
        String validator = machine.getCharacteristicNameRegex();
        Pattern pattern1 = Pattern.compile(validator);
        Matcher matcher1 = pattern1.matcher(lineOfText);
        String name="";
        while(matcher1.find())
        {
            name =(matcher1.group());
        }
        return name;
    }

    //Extract values from text by characteristic name regex. Returns it as map of double values
    private Map<String,List<Double>> readCharacteristicLine(Machine machine, String lineOfText){

        //Get requirements from machine instance
        String validator = machine.getCharacteristicNameRegex();
        LinkedHashSet<Integer> indexesOfValues = machine.getSetOfIndexesForValuesInTextLine();
        int maxArraySizeOfValuesInTextLine = machine.getValuesInTextLineMaxArraySize();

        String name;
        List<Double> preparedValues;
        List<String> values;
        Map<String,List<Double>> matchedTextLine = new HashMap<>();

        Pattern pattern1 = Pattern.compile(validator);
        Matcher matcher1 = pattern1.matcher(lineOfText);
        while(matcher1.find())
        {
            name =(matcher1.group());
            values = createCorrectArray(lineOfText, indexesOfValues);
            preparedValues = changeValuesType(values);
            matchedTextLine.put(name,preparedValues);
        }
        return matchedTextLine;
    }

    private List<String> createCorrectArray(String lineOfText, LinkedHashSet<Integer> indexesOfValues) {
        // final String VALUES_SEPARATOR = "\s+"; //Java 17
        final String VALUES_SEPARATOR = " +";
        List<String> strSplit = Arrays.asList(lineOfText.split(VALUES_SEPARATOR));
        int strSplitSize = strSplit.size();

        List<String> valuesList = new ArrayList<>();
        final String DEFAULT_STR_NUMBER = "0.00";
        //Create default array of values
        int maxIndex = indexesOfValues.stream().mapToInt(e->e).max().orElseThrow(NoClassDefFoundError::new);
        do{
            valuesList.add(DEFAULT_STR_NUMBER);
            maxIndex--;
        }while(maxIndex > 0);

        Iterator<Integer> iterator = indexesOfValues.iterator();
            for (int i = 0; i < indexesOfValues.size(); i++) {
                int nextData = iterator.next();
                //Check is exist value in text array
                if (nextData < strSplitSize){
                    if(isNumeric(strSplit.get(nextData))){
                        valuesList.set(i,strSplit.get(nextData));
                    }
                }
            }
        return valuesList;
    }

    private List<Double> changeValuesType(List<String> values) {
        List<Double> parsedVList = values
                .stream()
                .filter(e->isNumeric(e))
                .map(e-> numberParser(e))
                .collect(Collectors.toList());
        return parsedVList;
    }

    public static ReportCharacteristic createCharacteristics(Map<String,List<Double>>dataMap) {
        final int ACTUAL_VALUE_INDEX = 0;
        final int NOMINAL_VALUE_INDEX = 1;

        ReportCharacteristic characteristic = new ReportCharacteristic();
        for(String k: dataMap.keySet()){
            characteristic = new ReportCharacteristic( k,
                    dataMap.get(k).get(ACTUAL_VALUE_INDEX),
                    dataMap.get(k).get(NOMINAL_VALUE_INDEX),
                    dataMap.get(k).get(ACTUAL_VALUE_INDEX) - dataMap.get(k).get(NOMINAL_VALUE_INDEX));
        }
        return characteristic ;
    }

    private static Double numberParser(String inputNr) {
        NumberFormat formatter = new DecimalFormat("0.000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        String str = inputNr.replaceAll(",",".");
        Double nr = Double.parseDouble(str);
        //str = formatter.format(inputNr);
        return nr;
    }

    public boolean inputFileIsValid(String path){
        String inputFileExtension = inputFile.extension;
        boolean isValid = false;

        if(path == null)return isValid;

        if(stringIsPath(path)){
            int index = path.lastIndexOf(inputFileExtension);
            if(index !=-1){
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean stringIsPath(String inputString) {
        Path firstPath = (Path) Paths.get(inputString);
        if(java.nio.file.Files.exists(firstPath)){
            return true;
        }
        return false;
    }


}
