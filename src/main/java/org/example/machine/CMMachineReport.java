package org.example.machine;

import org.example.files.InputFileManager;
import org.example.report.ReportCharacteristic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CMMachineReport extends MachineReport {


    @Override
    public List<ReportCharacteristic> extractReportCharacteristics(StringBuilder file, InputFileManager objFileManager) throws IOException {

        List<ReportCharacteristic> characteristicsList = new ArrayList<>();
        Map<String, List<Double>> characteristicMap = new HashMap<>();
        ReportCharacteristic characteristic;
        String lineOfText, characteristicName;

        BufferedReader bufReader = new BufferedReader(new StringReader(file.toString()));


        //TODO wydzielic 2 metody wyzej i uruchamic jak w strategii
        while( (lineOfText=bufReader.readLine()) != null )
        {
            characteristicName = objFileManager.findCharacteristicByRegex(characteristicValidator, lineOfText);
            if(!characteristicName.equals("")){
                characteristicMap = objFileManager.readCharacteristicLine(characteristicValidator,lineOfText);
                characteristic = objFileManager.createCharacteristic(characteristicMap);
                characteristicsList.add(characteristic);
            }
        }
        characteristicsList = characteristicsList.stream()
                .filter(e->e!=null).toList();
        return characteristicsList;
    }

    @Override
    protected CharacteristicValidator setClassCharacteristicValidator() {
        return (CharacteristicValidator) new CMMachineCharacteristicValidator();
    }


/*
    public ReportHeader extractHeader() throws IOException {
        StringBuilder fileText = getRawReportText();
        String componentName="";
        String date="";
        String drawingNumber="";
        String partNumber1="";
        String partNumber2 ="";
        String programName ="";
        String operatorName ="";
        final int COMPONENT_NAME_LOC_INDEX = 3;
        final int DATE_LOC_INDEX = 4;
        final int DRAWING_NUMBER_LOC_INDEX = 9; // nie cale
        final int PART_NUMBER_1_LOC_INDEX = 10;
        final int PART_NUMBER_2_LOC_INDEX = 11;
        final int PROGRAM_NAME_LOC_INDEX = 13;
        final int OPERATOR_NAME_LOC_INDEX = 14; // nie cale

        //Get header from file text
        final String HEADER_END_SEPARATOR = "<--|-->";
        int headerEndIndex = fileText.indexOf(HEADER_END_SEPARATOR)+HEADER_END_SEPARATOR.length();
        String headerText = fileText.substring(0, headerEndIndex);
        BufferedReader bufReader = new BufferedReader(new StringReader(headerText));


        //Read every line of file text and get header information
        String line=null;
        int tmpLineCounter= 0;
        while( (line=bufReader.readLine()) != null )
        {
            switch(tmpLineCounter){
                case COMPONENT_NAME_LOC_INDEX:
                    componentName = line.substring(0);
                    break;
                case DATE_LOC_INDEX:
                    date = line.substring(0);
                    break;
                case DRAWING_NUMBER_LOC_INDEX:
                    drawingNumber = line.substring(0);
                    break;
                case PART_NUMBER_1_LOC_INDEX:
                    partNumber1 = line.substring(0);
                    break;
                case PART_NUMBER_2_LOC_INDEX:
                    partNumber2 = line.substring(0);
                    break;
                case PROGRAM_NAME_LOC_INDEX:
                    programName = line.substring(0);
                    break;
                case OPERATOR_NAME_LOC_INDEX:
                    operatorName = line.substring(0);
                    break;
                default:
            }
            tmpLineCounter++;
        }
        //Remove header from file text
        fileText.delete(0, headerEndIndex);

        return new ReportHeader(componentName, date, drawingNumber, partNumber1, partNumber2, programName, operatorName);
    }

    */

}
