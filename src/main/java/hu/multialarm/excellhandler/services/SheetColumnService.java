package hu.multialarm.excellhandler.services;

import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.model.excel.SheetColumn;
import hu.multialarm.excellhandler.repository.SheetColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SheetColumnService {
    private SheetColumnRepository repository;

    public SheetColumnService(SheetColumnRepository repository) {
        this.repository = repository;
    }

    public void save(SheetColumn sheetColumn) {
        repository.save(sheetColumn);
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
