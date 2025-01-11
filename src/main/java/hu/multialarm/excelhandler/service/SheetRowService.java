package hu.multialarm.excelhandler.service;

import hu.multialarm.excelhandler.model.excel.SheetColumn;
import hu.multialarm.excelhandler.model.excel.SheetRow;
import hu.multialarm.excelhandler.repository.SheetRowRepository;
import hu.multialarm.excelhandler.util.CellInspector;
import lombok.Data;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class SheetRowService {

    private CellInspector cellInspector;

    private SheetRowRepository sheetRowRepository;

    public SheetRowService(SheetRowRepository sheetRowRepository, CellInspector cellInspector) {
        this.sheetRowRepository = sheetRowRepository;
        this.cellInspector = cellInspector;
    }
    public List<SheetRow> createAndSaveSheetRows(Sheet sheet, List<SheetColumn> sheetColumnList) {
        //Count rows
        int rows = sheet.getLastRowNum();
        //Create sheet rows
        List<SheetRow> result = new ArrayList<>();

        for (int rowIterator = 0; rowIterator < rows; rowIterator++) {
            //get row Leng
            int columns = sheet.getRow(rowIterator).getLastCellNum();
            for (int cellIterator = 0; cellIterator < columns; cellIterator++) {
                SheetRow sheetRow = new SheetRow();
                sheetRow.setRowOrderNumber(rowIterator);
                sheetRow.setSheetColumn(sheetColumnList.get(cellIterator));
                sheetRow.setValueText(this.cellInspector.getCellValue(sheet.getRow(rowIterator).getCell(cellIterator)));
                result.add(this.sheetRowRepository.save(sheetRow));
            }
        }
        return result;
    }
}
