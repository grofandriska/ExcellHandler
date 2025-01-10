package hu.multialarm.excellhandler.util;


import hu.multialarm.excellhandler.model.excel.Excel;
import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.model.excel.SheetColumn;

import hu.multialarm.excellhandler.model.excel.SheetRow;
import hu.multialarm.excellhandler.services.ExcelService;
import hu.multialarm.excellhandler.services.SheetColumnService;
import hu.multialarm.excellhandler.services.SheetRowService;
import hu.multialarm.excellhandler.services.SheetService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
@NoArgsConstructor
@Getter
@Setter
public class Reader {

    private ExcelService excelService;
    private SheetService sheetService;
    private SheetColumnService sheetColumnService;
    private SheetRowService sheetRowService;

    public Reader(ExcelService excelService, SheetService sheetService, SheetColumnService sheetColumnService, SheetRowService sheetRowService) {
        this.excelService = excelService;
        this.sheetService = sheetService;
        this.sheetColumnService = sheetColumnService;
        this.sheetRowService = sheetRowService;
    }

    public void saveDataFromExcelToDatabases(String pathName) {
        Excel excel;
        FileInputStream file;
        Workbook workbook;

        try {
            file = new FileInputStream(pathName);

            workbook = WorkbookFactory.create(file);

            org.apache.poi.ss.usermodel.Sheet sheet;

            int numberOfSheets = workbook.getNumberOfSheets();

            excel = excelService.createExcelAndSave(pathName);
            //Iterate over sheets from workbook
            for (int i = 0; i < numberOfSheets; i++) {
                sheet = workbook.getSheetAt(i);
                //Save Sheet
                Sheet excelSheet = sheetService.createSheet(sheet.getSheetName(), excel);
                //Save Columns
                List<SheetColumn> sheetColumnList = sheetColumnService.createAndSave(sheet, excelSheet);
                //Save Rows
                sheetRowService.saveSheetRows(sheet, sheetColumnList);
            }
            workbook.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
