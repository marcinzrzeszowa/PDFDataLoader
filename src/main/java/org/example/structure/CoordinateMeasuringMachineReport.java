package org.example.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateMeasuringMachineReport implements MeasurementReport, MeasurementReportMethods {

    private StringBuilder rawInputText;
    private ReportHeader reportHeader;
    private ArrayList<ReportAttribute> reportAttributesList;

    /*
    Extracts attributes and parameters from text

    *([pP]\d+(_\d+)*(_[A-Za-z0-9]+)?)|([pP]\d+_[A-Za-z]+(_[A-Za-z0-9]+)?)|([pP][A-Za-z0-9]+_[A-Za-z0-9]+(_[A-Za-z0-9]+)*(_[A-Za-z]+)?)
    *
    * p1 p12 p321 p2_ p32_ p342_ p2_1 p32_23 p342_33 p2_12 p32_23 p342_44 p2_12 p32_23 p342_44 p1_1_3 p13_32_88 p123_323_881 p1_A p23_AB P234_ABC p2_h22mm p22_h2mm p232_h2.52mm p1_ref_ABC p12_ref_DA p133_ref_ABC
    *
    ([pP]\d+(_\d+)*(_[A-Za-z0-9]+)?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub więcej cyfr, z opcjonalnymi grupami podkreślenia i cyfr lub liter
     | - oznacza alternatywę, dopasuje alternatywne wzorce
     ([pP]\d+_[A-Za-z]+(_[A-Za-z0-9]+)?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub więcej cyfr, po których następuje podkreślenie, a następnie jedna lub więcej liter, z opcjonalnymi grupami podkreślenia i cyfr lub liter
     | - oznacza alternatywę, dopasuje alternatywne wzorce
     ([pP][A-Za-z0-9]+_[A-Za-z0-9]+(_[A-Za-z0-9]+)*(_[A-Za-z]+)?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub więcej liter lub cyfr, po których następuje podkreślenie, a następnie jedna lub więcej liter lub cyfr, z opcjonalnymi grupami podkreślenia i liter lub cyfr oraz z dodatkową grupą podkreślenia i liter na końcu
     * */
    private static final String REPORT_ATTRIBUTE_REGEX = "([pP]\\d+(_\\d+)*(_[A-Za-z0-9]+)?)|([pP]\\d+_[A-Za-z]+(_[A-Za-z0-9]+)?)|([pP][A-Za-z0-9]+_[A-Za-z0-9]+(_[A-Za-z0-9]+)*(_[A-Za-z]+)?)";


    public CoordinateMeasuringMachineReport(StringBuilder rawInputText) throws IOException {
        this.rawInputText = rawInputText;

        reportHeader = extractHeader(rawInputText);
        reportAttributesList = extractAttributes(rawInputText);
    }

    @Override
    public ArrayList<ReportAttribute> extractAttributes(StringBuilder rawText) throws IOException {
        ArrayList<String> properties;




        BufferedReader bufReader = new BufferedReader(new StringReader(rawText.toString()));

        String line=null;
        int tmpLineCounter= 0;
        while( (line=bufReader.readLine()) != null )
        {

            //TODO
            // properties = findPropertyByRegex(REPORT_ATTRIBUTE_REGEX, rawText.toString());

            tmpLineCounter++;
        }

        return null;
    }

    @Override
     public ReportHeader extractHeader(StringBuilder fileText) throws IOException {
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

    private static  ArrayList<String> findPropertyByRegex(String propRegex, String inStr){
        Pattern pattern1 = Pattern.compile(propRegex);
        Matcher matcher1 = pattern1.matcher(inStr);
        ArrayList<String> properties = new ArrayList<String>();
        while(matcher1.find())
        {
            if(properties.contains(matcher1.group())){

            }else{
                properties.add(matcher1.group());
            }
        }
        return properties;
    }

    @Override
    public ReportHeader getReportHeader() {
        return reportHeader;
    }

    @Override
    public ArrayList<ReportAttribute> getReportAttributesList() {
        return reportAttributesList;
    }
}
