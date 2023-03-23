package org.example.files;

import org.example.report.ReportCharacteristic;

import java.io.IOException;
import java.util.List;

public class OutputFileManager extends FileManager {
    private OutputFile outputFile;
    private TXTFile textFile;
    private XLSXFile excelFile;

    private static OutputFileManager instance = new OutputFileManager();

    private OutputFileManager() {
       this.textFile = new TXTFile();
       this.excelFile = new XLSXFile();
    }

    public static OutputFileManager getInstance(){
        return instance;
    }

    public void writeTXTReport(String filePath, List<ReportCharacteristic> characteristicList) {
        outputFile = textFile;
        try {
            outputFile.saveFile(filePath, characteristicList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeXLSXReport(String filePath, List<ReportCharacteristic> characteristicList) {
        outputFile = excelFile;
        try {
            outputFile.saveFile(filePath, characteristicList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeRawTXTFile(String filePath) {
        //TODO save
        //textFile.readRawFile(filePath);
    }
}
