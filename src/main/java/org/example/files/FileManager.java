package org.example.files;

import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;

abstract class FileManager {

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

    protected boolean isNumeric(String str) {
        String IS_NUMBER_REGEX = "-?\\d+(\\.\\d+)?";
        return str.matches(IS_NUMBER_REGEX);
    }

}
