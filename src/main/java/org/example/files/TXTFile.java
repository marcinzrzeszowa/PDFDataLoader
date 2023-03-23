package org.example.files;

import org.example.report.ReportCharacteristic;

import java.io.*;
import java.util.List;

class TXTFile extends OutputFile {

    public TXTFile() {
        this.extension = ".txt";
    }

    public String readRawFile(String path){
        StringBuilder rawText = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        BufferedReader bufReader = new BufferedReader(new StringReader(rawText.toString()));
        String line=null;
        int tmpLineCounter= 0;
        while(true)
        {
            try {
                if (!((line=bufReader.readLine()) != null)) break;
                sb.append(line).append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sb.toString();
    }


    private String createCSVFileFormat(List<ReportCharacteristic> reportAttributesList ){
        StringBuilder sb = new StringBuilder();
        sb.append("Cecha,Rzeczywisty,Nominalny,Różnica\n");
        for (ReportCharacteristic ra: reportAttributesList){
            sb
                    .append(ra.name()).append(",")
                    .append(ra.actual()).append(",")
                    .append(ra.nominal()).append(",")
                    .append(ra.difference()).append("\n");
        }
        return sb.toString();
    }

    @Override
    protected void saveFile(String filePath, List<ReportCharacteristic> characteristicList) throws IOException {

        File outFile = newFile(filePath);
        String content = createCSVFileFormat(characteristicList).toString();

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter( new FileWriter(outFile.getAbsolutePath().toString()));
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw != null) bw.close();
        }
    }
}
