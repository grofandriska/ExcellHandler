package hu.multialarm.excelhandler.controller;

import hu.multialarm.excelhandler.service.ExcelService;
import hu.multialarm.excelhandler.util.ExcelFileReader;
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
    private final ExcelFileReader excelFileReader;

    public ExcelRestController(ExcelFileReader excelFileReader) {
        this.excelFileReader = excelFileReader;
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

            excelFileReader.saveDataFromExcelToDatabases(file.getOriginalFilename());

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }
}
