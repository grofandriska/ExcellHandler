package hu.multialarm.excellhandler.controller;

import hu.multialarm.excellhandler.model.excell.Excel;
import hu.multialarm.excellhandler.services.ExcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExcelRestController {
	
	private final ExcelService excelService;
	
	public ExcelRestController(ExcelService excelService) {
		this.excelService = excelService;
	}
	
	@GetMapping("/upload")
	public ResponseEntity<String> uploadFile() {
		try {
			Excel dummy = new Excel();
			dummy.setFileName("Dummy");
			excelService.save(dummy);
			
			Excel dummy2 = new Excel();
			dummy2.setFileName("Mummy");
			excelService.save(dummy2);
			
			return ResponseEntity.ok("File uploaded and processed successfully.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to process file: " + e.getMessage());
		}
	}
	
	@GetMapping("/download")
	public ResponseEntity<?> download() {
		try {
			return ResponseEntity.ok(excelService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to retrieve files: " + e.getMessage());
		}
	}
	
	@GetMapping("/downloadName/{fileName}")
	public ResponseEntity<?> findByName(@PathVariable("fileName") String fileName) {
		try {
			return ResponseEntity.ok(excelService.findByFilename(fileName));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to retrieve files: " + e.getMessage());
		}
	}
	
	@GetMapping("/delete/{fileName}")
	public ResponseEntity<?> deleteByFilename(@PathVariable("fileName") String fileName) {
		try {
			excelService.deleteByName(fileName);
			return ResponseEntity.ok("Excell with filename: " + fileName + " was deleted successfully!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to delete file " + fileName + " ! See log:\n" + e.getMessage());
		}
	}
}
