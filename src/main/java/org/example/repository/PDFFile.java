package org.example.repository;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.example.structure.lib.Return;

import java.io.IOException;

class PDFFile {

    public StringBuilder parseFile(String filePath){
        StringBuilder sb = new StringBuilder();
        try {
            PdfReader reader = new PdfReader(filePath);
            String pageNrStr = PdfTextExtractor.getTextFromPage(reader,1);
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


    public Return readFileFragment(String filePath, int startPage, int endPage) {
        try {
            StringBuilder sb = new StringBuilder();

            PdfReader reader = new PdfReader(filePath);
            int pageNr = reader.getNumberOfPages();

            if (endPage < startPage || startPage < 1 || endPage > pageNr) {
                throw new IllegalArgumentException("Invalid page range");
            }
            for (int i = startPage; i <= endPage; i++) {
                String pageText = PdfTextExtractor.getTextFromPage(reader, i);
                sb.append(pageText);
            }
            reader.close();
            Return returnValue = new Return.ReturnBuilder(true).addInt(pageNr).addStrB(sb).build();
            return returnValue;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid page range");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
