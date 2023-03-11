package org.example.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public interface MeasurementReport {
    ReportHeader getReportHeader();
    ArrayList<ReportAttribute> getReportAttributesList();

    default String extractRawTextFile(StringBuilder rawText){
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

}
