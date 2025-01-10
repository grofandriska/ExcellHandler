package hu.multialarm.excelhandler.util;


import hu.multialarm.excelhandler.model.excel.Excel;
import hu.multialarm.excelhandler.model.excel.Sheet;
import hu.multialarm.excelhandler.model.excel.SheetColumn;

import hu.multialarm.excelhandler.service.ExcelService;
import hu.multialarm.excelhandler.service.SheetColumnService;
import hu.multialarm.excelhandler.service.SheetRowService;
import hu.multialarm.excelhandler.service.SheetService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;

import org.springframework.stereotype.Service;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelFileReader {

    private final ExcelService excelService;
    private final SheetService sheetService;
    private final SheetRowService sheetRowService;
    private final SheetColumnService sheetColumnService;

    public ExcelFileReader(ExcelService excelService,
                           SheetService sheetService,
                           SheetColumnService sheetColumnService,
                           SheetRowService sheetRowService) {
        this.excelService = excelService;
        this.sheetService = sheetService;
        this.sheetColumnService = sheetColumnService;
        this.sheetRowService = sheetRowService;
    }

    public void saveDataFromExcelToDatabases(String pathName) {
        Excel excel;
        FileInputStream file;
        Workbook workbook;
        org.apache.poi.ss.usermodel.Sheet sheet;

        try {
            //Save Excel file into database
            file = new FileInputStream(pathName);
            excel = this.excelService.createExcelAndSave(pathName);

            //Iterate over sheets from workbook
            workbook = WorkbookFactory.create(file);
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                sheet = workbook.getSheetAt(i);
                //Save Sheet
                Sheet excelSheet = this.sheetService.createSheet(sheet.getSheetName(), excel);
                //Save Columns
                List<SheetColumn> sheetColumnList = this.sheetColumnService.createAndSave(sheet, excelSheet);
                //Save Rows
                this.sheetRowService.saveSheetRows(sheet, sheetColumnList);
            }
            workbook.close();
            file.close();
            //TODO:ERROR MESSAGES
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
