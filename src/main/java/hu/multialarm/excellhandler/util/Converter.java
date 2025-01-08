package hu.multialarm.excellhandler.util;

import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.repository.SheetRepository;

import java.util.Optional;

public class Converter {

    private SheetRepository repository;

    public Sheet convertToSheet(Long id) {
        Optional<Sheet> sheet;
        Sheet resultSheet= new Sheet();
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
}
