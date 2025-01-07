package hu.multialarm.excellhandler.services;

import hu.multialarm.excellhandler.model.excell.Excel;
import hu.multialarm.excellhandler.repository.ExcellRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcelService {
	
	private ExcellRepository repository;
	
	public ExcelService(ExcellRepository repository) {
		this.repository = repository;
	}
	
	public void save(Excel excel) {
		this.repository.save(excel);
	}
	
	public List<Excel> findAll() {
		return repository.findAll();
	}
	
	public Optional<Excel> findByID(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Excel> findByFilename(String fileName) {
		return repository.findByFileName(fileName);
	}
	
	public void deleteEntity(Excel excel) {
		repository.delete(excel);
	}
	
	public void deleteByID(Long id) {
		repository.deleteById(id);
	}
	
	public void deleteByName(String fileName) {
		repository.deleteByFileName(fileName);
	}
}
