package hu.multialarm.excellhandler.controller;

import hu.multialarm.excellhandler.services.ExcelService;
import hu.multialarm.excellhandler.util.Reader;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api/excel")
public class ExcelRestController {

    private Reader reader;
    private ExcelService excelService;

    public ExcelRestController(ExcelService excelService,Reader reader) {
        this.excelService = excelService;
        this.reader = reader;
    }

    @GetMapping("/index")
    public String index() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected!");
        }
        try {
            String uploadDir = "/";
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);
            reader.saveDataFromExcelToDatabases(file.getOriginalFilename());
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
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
