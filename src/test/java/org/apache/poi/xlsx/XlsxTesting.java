package org.apache.poi.xlsx;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;


/**
 * Created by User on 12/18/13.
 */
public class XlsxTesting {


    @Test
    public void CreateXlsxTest() throws IOException, InvalidFormatException {
        CreateNewWorkBook();
        writeXLSXFile();
    }

    public void CreateNewWorkBook() throws IOException {
//        Workbook wb = new HSSFWorkbook();
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        wb.write(fileOut);
//        fileOut.close();

        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("src/test/resources/workbook.xlsx");
        wb.createSheet("rent");
        wb.write(fileOut);
        fileOut.close();
    }


    public static void writeXLSXFile() throws IOException, InvalidFormatException {
        String excelFileName = "src/test/resources/workbook.xlsx";//name of excel file

        String sheetName = "Sheet1";//name of sheet
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(new File(excelFileName)));

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = wb.getSheetAt(0);
        //XSSFWorkbook wb = new XSSFWorkbook(excelFileName);
        //XSSFSheet sheet = wb.createSheet(sheetName) ;

//iterating r number of rows
        for (int r=0;r < 5; r++ )
        {
            XSSFRow row = sheet.createRow(r);

//iterating c number of columns
            for (int c=0;c < 5; c++ )
            {
                XSSFCell cell = row.createCell(c);
                cell.setCellValue("Cell "+r+" "+c);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(excelFileName);

//write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

}
