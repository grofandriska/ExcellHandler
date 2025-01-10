package hu.multialarm.excellhandler.services;

import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.model.excel.SheetColumn;
import hu.multialarm.excellhandler.repository.SheetColumnRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class SheetColumnService {
    private SheetColumnRepository repository;


    public SheetColumnService(SheetColumnRepository repository) {
        this.repository = repository;
    }

    public List<SheetColumn>  createAndSave(org.apache.poi.ss.usermodel.Sheet sheet, Sheet sheetColumnSheet){
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
            sheetColumn.setColumnType(getCellType(cellList.get(i1)));
            sheetColumnList.add(repository.save(sheetColumn));
        }
        return sheetColumnList;
    }

    public String getCellType(Cell cell) {
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

    public SheetColumn save(SheetColumn sheetColumn) {
        return  repository.save(sheetColumn);
    }

    public List<SheetColumn> listAllSheetColumn() {
        return repository.findAll();
    }

    public List<SheetColumn> getAllSheetColumnBySheetId(Sheet sheet) {
        return this.repository.findSheetColumnsBySheet(sheet);
    }
    public Optional<SheetColumn> getSheetColumnById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteEntity(SheetColumn entity) {
        repository.delete(entity);
    }
}
