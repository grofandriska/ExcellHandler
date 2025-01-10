package hu.multialarm.excellhandler.util;

import hu.multialarm.excellhandler.model.excel.Excel;
import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.repository.ExcelRepository;
import hu.multialarm.excellhandler.repository.SheetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Converter {

    private SheetRepository repository;
    private ExcelRepository excelRepository;

    public Converter(SheetRepository repository, ExcelRepository excelRepository) {
        this.repository = repository;
        this.excelRepository = excelRepository;
    }

    public Sheet convertToSheet(Long id) {
        Optional<Sheet> sheet;
        Sheet resultSheet = new Sheet();
        try {
            sheet = repository.findById(id);
            if (sheet.isPresent()) {
                resultSheet.setSheetName(sheet.get().getSheetName());
                resultSheet.setExcel(sheet.get().getExcel());
                resultSheet.setId(sheet.get().getId());
            }
        } catch (Exception e) {
            System.out.println("Error while converting from Optional<Sheet> to Sheet object in SheetService. " + e);
        }
        return resultSheet;
    }

    public Excel convertToExcel(Long id) {
        Optional<Excel> excel;
        Excel resultExcel = new Excel();
        try {
            excel = excelRepository.findById(id);
            if (excel.isPresent()) {
                resultExcel.setId(excel.get().getId());
                resultExcel.setFileName(excel.get().getFileName());
            }
        } catch (Exception e) {
            System.out.println("Hiba az excel konvertálás során! : "+e);
        }
        return resultExcel;

    }
}
