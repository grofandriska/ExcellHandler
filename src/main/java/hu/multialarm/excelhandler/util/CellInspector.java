package hu.multialarm.excelhandler.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class CellInspector {

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
                    dataType = "Unknown";
            }
        } else {
            throw new NullPointerException("A cella NULL volt!");
        }
        return dataType;
    }
    public String getCellValue(Cell cell) {
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
