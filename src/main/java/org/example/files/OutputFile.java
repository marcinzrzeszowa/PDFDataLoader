package org.example.files;

import org.example.report.ReportCharacteristic;

import java.io.File;
import java.io.IOException;
import java.util.List;

abstract class OutputFile {
    protected String extension;
    private final String INPUT_FILE_EXTENSION =".pdf";

    protected abstract void saveFile(String filePath, List<ReportCharacteristic> characteristicList) throws IOException;

    private void checkFile(String filePath){
        //TODO
    }

    protected File newFile(String filePath) {
        checkFile(filePath);
        File file = new File(filePath);
        String inFileName = file.getName();
        String outFileName = inFileName.replaceFirst(INPUT_FILE_EXTENSION, extension);

        String inputFileLocation = file.getAbsolutePath().toString();
        String outFileLocation = inputFileLocation.replaceFirst(inFileName, outFileName);
        System.out.println("Created file: " + outFileName + " in location "+ outFileLocation);

        return new File(outFileLocation);
    }
}
