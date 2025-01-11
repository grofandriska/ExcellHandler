package hu.multialarm.excelhandler.service;

import hu.multialarm.excelhandler.model.excel.Sheet;
import hu.multialarm.excelhandler.model.excel.SheetColumn;
import hu.multialarm.excelhandler.repository.SheetColumnRepository;
import hu.multialarm.excelhandler.util.CellInspector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class SheetColumnService {

    private final CellInspector cellInspector;
    private final SheetColumnRepository repository;


    public SheetColumnService(CellInspector cellInspector, SheetColumnRepository repository) {
        this.cellInspector = cellInspector;
        this.repository = repository;
    }

    public List<SheetColumn> createAndSaveSheetColumn(org.apache.poi.ss.usermodel.Sheet sheet, Sheet sheetColumnSheet) {

        SheetColumn sheetColumn;
        //Storing cells for their types
        List<Cell> cellList = new ArrayList<>();
        //Set of sheets columns
        List<SheetColumn> sheetColumnList = new ArrayList<>();
        //Get header row
        Row headerRow = sheet.getRow(0);
        //Get headers and create SheetColumns
        try {
            for (int iterator = 0; iterator < headerRow.getLastCellNum(); iterator++) {
                String columnName = "";
                //Create column
                sheetColumn = new SheetColumn();
                //Set parent sheet
                sheetColumn.setSheet(sheetColumnSheet);
                try {
                    columnName = headerRow.getCell(iterator).getStringCellValue();
                } catch (Exception e) {
                    System.out.println("Hiányos Headerek!");;
                }
                //Set columnName
                sheetColumn.setColumnName(columnName);
                //Set the number of column
                sheetColumn.setOrderNumber(iterator);
                //Get types
                Row tempRowForTypes = sheet.getRow(1);
                //Iterate over 2nd row
                Iterator<Cell> cellIteratorFoType = tempRowForTypes.cellIterator();
                // Get each cell from the 2nd row
                while (cellIteratorFoType.hasNext()) {
                    Cell cell = cellIteratorFoType.next();
                    //Add each cell
                    cellList.add(cell);
                }
                //Set type of column based on
                sheetColumn.setColumnType(this.cellInspector.getCellType(cellList.get(iterator)));
                //return sheets columns
                sheetColumnList.add(this.repository.save(sheetColumn));
            }
        } catch (Exception e) {

        }

        return sheetColumnList;
    }
}
