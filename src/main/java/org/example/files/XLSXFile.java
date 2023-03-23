package org.example.files;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.report.ReportCharacteristic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class XLSXFile extends OutputFile{

    public XLSXFile() {
        this.extension = ".xlsx";
    }

    @Override
    protected void saveFile(String filePath, List<ReportCharacteristic> characteristicList) throws IOException {

        File outFile = newFile(filePath);

        String spreadsheetName ="name";

        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet(spreadsheetName);

        // creating a row object
        XSSFRow row;

        int rowId= 0;
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
