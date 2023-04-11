package org.example.service;

public class OMMachineService extends MachineService {

    private final String NAME_REGEX ="(?<=\\()(.*?)(?=\\))";
    private final String BEGIN_REGEX = "Typ Nazwa Wymiar … GT DT Wartoś… Koment… Wynik w GO Grafika";
    private final String FINISH_REGEX = "";


    @Override
    public String getCharacteristicNameRegex() {
        return NAME_REGEX;
    }

    @Override
    public String getBeginReportValuesRegex() { return BEGIN_REGEX;
    }

    @Override
    public String getFinishReportValuesRegex() {return FINISH_REGEX;
    }
}
