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

    public List<SheetColumn> createAndSave(org.apache.poi.ss.usermodel.Sheet sheet, Sheet sheetColumnSheet){
        SheetColumn sheetColumn;
        Row row = sheet.getRow(0);
        List<SheetColumn> sheetColumnList = new ArrayList<>();
        List<Cell> cellList = new ArrayList<>();
        for (int i1 = 0; i1 < row.getLastCellNum(); i1++) {
            sheetColumn = new SheetColumn();
            sheetColumn.setSheet(sheetColumnSheet);
            sheetColumn.setColumnName(row.getCell(i1).getStringCellValue());
            sheetColumn.setOrderNumber(i1);

            Row tempRowForTypes = sheet.getRow(1);
            Iterator<Cell> cellIteratorFoType = tempRowForTypes.cellIterator();
            while (cellIteratorFoType.hasNext()) {
                Cell cell = cellIteratorFoType.next();
                cellList.add(cell);
            }
            sheetColumn.setColumnType(this.cellInspector.getCellType(cellList.get(i1)));
            sheetColumnList.add(this.repository.save(sheetColumn));
        }
        return sheetColumnList;
    }


}
