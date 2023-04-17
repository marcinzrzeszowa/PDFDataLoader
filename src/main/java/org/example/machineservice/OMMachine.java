package org.example.machineservice;

import java.util.LinkedHashSet;
import java.util.Map;

class OMMachine extends Machine {

    private final String NAME_REGEX ="(?<=\\()(.*?)(?=\\))";
    private final String BEGIN_REGEX = "Typ Nazwa Wymiar … GT DT Wartoś… Koment… Wynik w GO Grafika";
    private final String FINISH_REGEX = "";

    final int ACTUAL_VALUE_INDEX = 4;
    final int NOMINAL_VALUE_INDEX = 1;
    private LinkedHashSet<Integer> characteristicIndexInText;
    private int MAX_TEXT_LINE_ARRAY_SIZE = 8;

    public OMMachine() {
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
    public LinkedHashSet<Integer> getSetOfIndexesForValuesInTextLine() {
        return characteristicIndexInText;
    }

    @Override
    public int getValuesInTextLineMaxArraySize() {
        return MAX_TEXT_LINE_ARRAY_SIZE;
    }
}
