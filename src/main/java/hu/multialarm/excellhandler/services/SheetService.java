package hu.multialarm.excellhandler.services;

import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.model.excel.SheetColumn;
import hu.multialarm.excellhandler.repository.SheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetService {

    private SheetRepository repository;

    public SheetService(SheetRepository repository) {
        this.repository = repository;
    }

    public Optional<Sheet> findBySheetId(Long id) {
        return repository.findById(id);
    }

    public void saveSheet(Sheet sheet) {
        repository.save(sheet);
    }

    public List<Sheet> listAllSheet() {
        return repository.findAll();
    }

    public void deleteSheet(Long id) {
        repository.deleteById(id);
    }

}
