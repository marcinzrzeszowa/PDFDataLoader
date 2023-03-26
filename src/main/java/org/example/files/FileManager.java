package org.example.files;

import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;

abstract class FileManager {

    private PDFFile pdfFile;

    public FileManager() {
        this.pdfFile = new PDFFile();
    }

    protected static boolean isEditable(String filePath) {
        try {
            PdfReader reader = new PdfReader(filePath);
            boolean isEditable = !reader.isEncrypted() || reader.isOpenedWithFullPermissions();
            reader.close();
            return isEditable;
        } catch (InvalidPdfException e) {
            System.err.println("Invalid PDF file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error while checking if PDF is editable: " + e.getMessage());
        }
        return false;
    }

    protected StringBuilder parseFile(String filePath){
        StringBuilder parsedText = new StringBuilder();
        if(isEditable(filePath)) {
            parsedText = pdfFile.parseFile(filePath);
        }
        return parsedText;
    }


    protected boolean isNumeric(String str) {
        String IS_NUMBER_REGEX = "-?\\d+(\\.\\d+)?";
        return str.matches(IS_NUMBER_REGEX);
    }

}
