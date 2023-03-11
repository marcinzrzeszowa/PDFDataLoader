package org.example;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;


class DataReader {



    public static StringBuilder readFile(String fileName){
        File file = takeFile(fileName);
        StringBuilder sb = new StringBuilder();

        try {
            PdfReader reader = new PdfReader(file.getPath());
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

    private static File takeFile(String name){
        URL res = DataReader.class.getClassLoader().getResource(name);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}
