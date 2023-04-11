package org.example.repository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.report.ReportCharacteristic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

class XLSXFile extends OutputFile{

    public XLSXFile() {
        this.extension = ".xlsx";
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


        //TODO wydzielic tworzenie arkusza i formatowanie danych

        File outFile = newFile(filePath);

        String spreadsheetName ="name";

        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet(spreadsheetName);

        // creating a row object
        XSSFRow row;


        row = spreadsheet.createRow(0);
        Cell c1 = row.createCell(0);
        c1.setCellValue("Cecha");
        Cell c2 = row.createCell(1);
        c2.setCellValue("Rzeczywista");
        Cell c3 = row.createCell(2);
        c3.setCellValue("Nominalna");
        Cell c4 = row.createCell(3);
        c4.setCellValue("Różnica");

        int rowId= 1;
        for(ReportCharacteristic ra: characteristicList){
            row = spreadsheet.createRow(rowId++);
            int cellId= 0;
            Cell cell1 = row.createCell(cellId++);
            cell1.setCellValue(ra.name());

            Cell cell2 = row.createCell(cellId++);
            cell2.setCellValue(ra.actual());

            Cell cell3 = row.createCell(cellId++);
            cell3.setCellValue(ra.nominal());

            Cell cell4 = row.createCell(cellId++);
            cell4.setCellValue(ra.difference());
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outFile.getAbsolutePath().toString());
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
