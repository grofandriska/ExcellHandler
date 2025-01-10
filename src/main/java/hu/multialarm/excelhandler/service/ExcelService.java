package hu.multialarm.excelhandler.service;

import hu.multialarm.excelhandler.model.excel.Excel;

import hu.multialarm.excelhandler.repository.ExcelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcelService {

    private ExcelRepository repository;

    public ExcelService(ExcelRepository repository) {
        this.repository = repository;
    }

    public Excel save(Excel excel) {
        return this.repository.save(excel);
    }

    public Excel createExcelAndSave(String fileName) {
        Excel excel = new Excel();
        excel.setFileName(fileName);
        return this.repository.save(excel);
    }

    public List<Excel> findAll() {
        return repository.findAll();
    }

    public Optional<Excel> findById(Long id) {
        return repository.findById(id);
    }

    public List<Excel> findByFilename(String fileName) {
        return repository.findByFileName(fileName);
    }

    public void deleteByFileName(String fileName) {
        repository.deleteByFileName(fileName);
    }

    public void deleteEntity(Excel excel) {
        repository.delete(excel);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
