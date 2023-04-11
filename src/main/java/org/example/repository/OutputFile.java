package org.example.repository;

import org.example.report.ReportCharacteristic;

import java.io.*;
import java.util.List;

abstract class OutputFile {
    protected String extension;
    private final String INPUT_FILE_EXTENSION =".pdf";


    protected abstract void saveFile(String filePath, List<ReportCharacteristic> characteristicList) throws IOException;

    protected File newFile(String filePath) {
            File file = new File(filePath);
            String inFileName = file.getName();
            String outFileName = inFileName.replaceFirst(INPUT_FILE_EXTENSION, extension);
            String inputFileLocation = file.getAbsolutePath().toString();
            String outFileLocation = inputFileLocation.replaceFirst(inFileName, outFileName);
            System.out.println("Utworzono plik: " + outFileName);
            System.out.println(outFileLocation);
            return new File(outFileLocation);
    }

    public void saveRawFile(String path, StringBuilder contentText) throws IOException {
        File outFile = newFile(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter( new FileWriter(outFile.getAbsolutePath().toString()));
            bw.write(contentText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw != null) bw.close();
        }
    }

}
