package org.example.files;

import org.example.report.ReportCharacteristic;

import java.io.IOException;
import java.util.List;

public class OutputFileManager extends FileManager {
    private OutputFile outputFile;
    private static OutputFileManager instance = new OutputFileManager();

    public static OutputFileManager getInstance(){
        return instance;
    }

    public void writeTXTReport(String filePath, List<ReportCharacteristic> characteristicList) {
        outputFile = new TXTFile();
        try {
            outputFile.saveFile(filePath, characteristicList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeXLSXReport(String filePath, List<ReportCharacteristic> characteristicList) {
        outputFile = new XLSXFile();
        try {
            outputFile.saveFile(filePath, characteristicList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeRawTXTFile(String filePath) {
        outputFile = new TXTFile();
        try{
            StringBuilder parsedText  =parseFile(filePath);
            outputFile.saveRawFile(filePath, parsedText);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
