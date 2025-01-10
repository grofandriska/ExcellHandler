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

    public String saveDataFromExcelToDatabases(String pathName) {
        FileInputStream file = null;
        Workbook workbook = null;

        Excel excel;

        try {
            file = new FileInputStream(new File(pathName));
            excel = excelService.createExcelAndSave(pathName);

            workbook = WorkbookFactory.create(file);

            org.apache.poi.ss.usermodel.Sheet sheet;

            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                //*****************************Create and Save Sheet******************/////
                sheet = workbook.getSheetAt(i);

                Sheet sheet2 = sheetService.createSheet(sheet.getSheetName(), excel);

                //***************************** Create and Save Columns* *****************/////
                SheetColumn sheetColumn;

                Row row = sheet.getRow(0);
                List<SheetColumn> sheetColumnList = new ArrayList<>();
                List<Cell> cellList = new ArrayList<>();
                for (int i1 = 0; i1 < row.getLastCellNum(); i1++) {

                    sheetColumn = new SheetColumn();
                    sheetColumn.setSheet(sheet2);
                    sheetColumn.setColumnName(row.getCell(i1).getStringCellValue());
                    sheetColumn.setOrderNumber(i1);

                    Row row1 = sheet.getRow(1);
                    Iterator<Cell> cellIteratorFoType = row1.cellIterator();
                    while (cellIteratorFoType.hasNext()) {
                        Cell cell = cellIteratorFoType.next();
                        cellList.add(cell);
                    }
                    sheetColumn.setColumnType(getCellType(cellList.get(i1)));
                    sheetColumnList.add(sheetColumnService.save(sheetColumn));
                }
                int rows = sheet.getLastRowNum();
                int columns = row.getLastCellNum();

                for (int ix = 0; ix < rows; ix++) {
                    for (int j = 0; j < columns; j++) {
                        SheetRow sheetRow = new SheetRow();
                        sheetRow.setRowOrderNumber(ix);

                        sheetRow.setSheetColumn(sheetColumnList.get(j));

                        sheetRow.setValueText(getCellType(sheet.getRow(ix).getCell(j)));
                        sheetRowService.save(sheetRow);
                    }
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Siker";
    }

    private String getCellType(Cell cell) {
        String dataType;
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    dataType = "String";
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        dataType = "Date";
                    } else {
                        dataType = "Numeric";
                    }
                    break;
                case BOOLEAN:
                    dataType = "Boolean";
                    break;
                case FORMULA:
                    dataType = "Formula";
                    break;
                default:
                    System.out.println("I am Unknown");
                    dataType = "Unknown";
            }
        } else {
            throw new NullPointerException("A cella NULL volt!");
        }
        return dataType;
    }
}
