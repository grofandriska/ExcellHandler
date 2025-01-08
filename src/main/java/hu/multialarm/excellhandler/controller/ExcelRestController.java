package hu.multialarm.excellhandler.controller;

import hu.multialarm.excellhandler.model.excel.Excel;
import hu.multialarm.excellhandler.services.ExcelService;
import hu.multialarm.excellhandler.util.Utilitizer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
@AllArgsConstructor
public class ExcelRestController {

    private ExcelService excelService;

    private Utilitizer utilitizer;

    @GetMapping("/upload")
    public ResponseEntity<String> uploadExcel() {
        try {
            List<Excel> list = utilitizer.generateExcellList();
            for (Excel e : list) {
                excelService.save(e);
            }
            return ResponseEntity.ok("Excel data uploaded and processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to process file: " + e.getMessage());
        }
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> listAllExcell() {
        try {
            return ResponseEntity.ok(excelService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to retrieve excel data: " + e.getMessage());
        }
    }

    @GetMapping("/find/{fileName}")
    public ResponseEntity<?> findByFileName(@PathVariable("fileName") String fileName) {
        try {
            return ResponseEntity.ok(excelService.findByFilename(fileName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to retrieve '" + fileName + "'s excel data : " + e.getMessage());
        }
    }

    @Transactional
    @GetMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteByFilename(@PathVariable("fileName") String fileName) {
        try {
            excelService.deleteByFileName(fileName);
            return ResponseEntity.ok("Excel with filename: " + fileName + " was deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete excel data in '" + fileName + "' !See log:\n" + e.getMessage());
        }
    }
}
