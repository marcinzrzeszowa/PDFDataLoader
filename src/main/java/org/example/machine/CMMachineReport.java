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
        /*
      P_12_234_1^1 ,P_12 , P_12_1 , p_1, p2_1^1

      ^ oznacza początek ciągu
      [pP] oznacza, że wyrażenie musi zaczynać się na P lub P
      _?\.?^? oznacza, że 0 lib więcej _ . ^
      [0-9]+ oznacza 1 lub wiecej
      [0-9]* oznacza 0 lub wiecej
      * */
    private final String NAME_REGEX_5 ="^[pP]_?\\.?^?[0-9]+_?\\.?\\^?[0-9]*_?\\.?\\^?[0-9]*";

    @Override
    public String getStringCharacteristicNameRegex() {
        return NAME_REGEX_5;
    }
}
