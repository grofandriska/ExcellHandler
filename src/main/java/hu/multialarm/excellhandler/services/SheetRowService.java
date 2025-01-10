package hu.multialarm.excellhandler.services;

import hu.multialarm.excellhandler.model.excel.SheetColumn;
import hu.multialarm.excellhandler.model.excel.SheetRow;
import hu.multialarm.excellhandler.repository.SheetRowRepository;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class SheetRowService {

    private SheetRowRepository sheetRowRepository;

    public SheetRowService(SheetRowRepository sheetRowRepository) {
        this.sheetRowRepository = sheetRowRepository;
    }

    public SheetRow save(SheetRow sheetRow) {
        return sheetRowRepository.save(sheetRow);
    }

    private SheetRow createAndSaveSheetRow(SheetRow sheetRow) {

        return sheetRowRepository.save(sheetRow);

    }

    public List<SheetRow> saveSheetRows(Sheet sheet, List<SheetColumn> sheetColumnList) {
        int rows = sheet.getLastRowNum();
        List<SheetRow> result = new ArrayList<>();
        for (int rowIterator = 0; rowIterator < rows; rowIterator++) {
            int columns = sheet.getRow(rowIterator).getLastCellNum();
            for (int cellIterator = 0; cellIterator < columns; cellIterator++) {
                SheetRow sheetRow = new SheetRow();
                sheetRow.setRowOrderNumber(rowIterator);
                sheetRow.setSheetColumn(sheetColumnList.get(cellIterator));
                sheetRow.setValueText(getCellTypeAndValue(sheet.getRow(rowIterator).getCell(cellIterator)));
                result.add(sheetRowRepository.save(sheetRow));
            }
        }
        return result;
    }

    public String getCellTypeAndValue(Cell cell) {
        String dataType;
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    dataType = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        dataType = String.valueOf(cell.getLocalDateTimeCellValue());
                    } else {
                        dataType = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    dataType = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    dataType = cell.getCellFormula();
                    break;
                default:
                    dataType = "Unknown";
            }
        } else {
            throw new NullPointerException("A cella NULL volt!");
        }
        return dataType;
    }
}
