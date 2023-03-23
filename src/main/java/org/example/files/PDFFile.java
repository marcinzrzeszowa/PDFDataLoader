package org.example.files;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.example.machine.MachineReport;
import org.example.report.ReportCharacteristic;

import java.io.IOException;
import java.util.List;

class PDFFile {

    public StringBuilder parseFile(String filePath){
        StringBuilder sb = new StringBuilder();
        try {
            PdfReader reader = new PdfReader(filePath);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                String pageText = PdfTextExtractor.getTextFromPage(reader, i);
                sb.append(pageText);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb;
    }
}
