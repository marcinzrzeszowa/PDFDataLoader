package org.example.service;

public class CMMachineService extends MachineService {

    private final String NAME_REGEX ="^[pP]_?\\.?^?[0-9]+_?\\.?\\^?[0-9]*_?\\.?\\^?[0-9]*";
    private final String BEGIN_REGEX = " Name ID Actual Nominal pos Tol neg Tol Diff <--|-->";
    private final String FINISH_REGEX = "";

    @Override
    public String getCharacteristicNameRegex() {return NAME_REGEX;
    }

    @Override
    public String getBeginReportValuesRegex() { return BEGIN_REGEX;
    }

    @Override
    public String getFinishReportValuesRegex() {return FINISH_REGEX;
    }
}
