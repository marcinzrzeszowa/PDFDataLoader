package org.example.machine;

import org.example.files.InputFileManager;
import org.example.report.ReportCharacteristic;

import java.util.List;

public class OpticalMeasuringMachineReport extends MachineReport {

    @Override
    public List<ReportCharacteristic> extractReportCharacteristics(StringBuilder file, InputFileManager fileManager) {
     return null;
    }
}
