package hu.multialarm.excelhandler.service;

import hu.multialarm.excelhandler.model.excel.Excel;
import hu.multialarm.excelhandler.model.excel.Sheet;
import hu.multialarm.excelhandler.repository.SheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetService {

    private SheetRepository repository;

    public SheetService(SheetRepository repository) {
        this.repository = repository;
    }

    public Sheet saveSheet(Sheet sheet) {
       return repository.save(sheet);
    }

    public Optional<Sheet> findBySheetId(Long id) {
        return repository.findById(id);
    }
    public Sheet createAndSaveSheet(String sheetName, Excel excel) {
        Sheet sheet = new Sheet();
        sheet.setSheetName(sheetName);
        sheet.setExcel(excel);
        return saveSheet(sheet);
    }
    public List<Sheet> listAllSheet() {
        return repository.findAll();
    }

    public void deleteSheet(Long id) {
        repository.deleteById(id);
    }

}
