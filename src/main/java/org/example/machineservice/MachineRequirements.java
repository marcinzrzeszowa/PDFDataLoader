package org.example.machineservice;

import java.util.LinkedHashSet;

interface MachineRequirements {

     /*
     * Define regular expression describing
     * Characteristic Name in input file
    */
    String getCharacteristicNameRegex();

    /*
     * Define regular expression describing
     * begin text line where characteristics starts in input file
     */
    String getBeginReportCutRegex();

    /*
     * Define regular expression describing
     * finish text line where characteristic ends in input file
     */
    String getFinishReportCutRegex();

    /*
     * Define maximum length of Text array.
     * Text array represents values for one characteristic
     */
    int getValuesInTextLineMaxArraySize();

    /*
     * Create end return LinkedHashSet of indexes values
     * WARNING !! Define just 2 elements. Extra element will be represented as 0.0 value in report file
     *
     * FIRST element of list define index number of NOMINAL value located in text array. Text array represents values for one characteristic
     * SECOND element of list define index number of ACTUAL value located in text array.
     *
     * (Define indexes by parsing RAW_TEXT file for interesting report file)
     */
    LinkedHashSet<Integer> getSetOfIndexesForValuesInTextLine();


}
