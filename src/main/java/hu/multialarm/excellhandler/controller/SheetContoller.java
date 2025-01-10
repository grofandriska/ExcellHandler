package hu.multialarm.excellhandler.controller;

import hu.multialarm.excellhandler.model.excel.Excel;
import hu.multialarm.excellhandler.services.SheetService;
import hu.multialarm.excellhandler.util.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sheet")
public class SheetContoller {

    private SheetService sheetService;

    public SheetContoller(SheetService sheetService) {
        this.sheetService = sheetService;
    }
    @GetMapping("/find/all")
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok(sheetService.listAllSheet());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to process file: " + e.getMessage());
        }
    }
}
