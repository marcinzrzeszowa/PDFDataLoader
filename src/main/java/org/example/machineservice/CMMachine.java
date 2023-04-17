package org.example.machineservice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class CMMachine extends Machine {

    private final String NAME_REGEX ="^[pP]_?\\.?^?[0-9]+_?\\.?\\^?[0-9]*_?\\.?\\^?[0-9]*";
    private final String BEGIN_REGEX = " Name ID Actual Nominal pos Tol neg Tol Diff <--|-->";
    private final String FINISH_REGEX = "";
    final int ACTUAL_VALUE_INDEX = 2;
    final int NOMINAL_VALUE_INDEX = 3;
    private int MAX_TEXT_LINE_ARRAY_SIZE = 8;
    private LinkedHashSet<Integer> characteristicIndexInText;


    public CMMachine() {
        this.characteristicIndexInText = new LinkedHashSet<>();
        characteristicIndexInText.add(ACTUAL_VALUE_INDEX);
        characteristicIndexInText.add(NOMINAL_VALUE_INDEX);
    }


    @Override
    public String getCharacteristicNameRegex() { return NAME_REGEX;}

    @Override
    public String getBeginReportCutRegex() { return BEGIN_REGEX;}

    @Override
    public String getFinishReportCutRegex() { return FINISH_REGEX;}

   @Override
   public LinkedHashSet<Integer> getSetOfIndexesForValuesInTextLine() { return characteristicIndexInText;}

    @Override
    public int getValuesInTextLineMaxArraySize() {
        return MAX_TEXT_LINE_ARRAY_SIZE;
    }
}
