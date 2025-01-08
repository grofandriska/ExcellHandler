package hu.multialarm.excellhandler.controller;


import hu.multialarm.excellhandler.services.SheetColumnService;
import hu.multialarm.excellhandler.services.SheetService;

import hu.multialarm.excellhandler.util.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/sheet/column")
public class SheetColumnController {

    private Converter converter;
    private SheetColumnService sheetColumnService;

    public SheetColumnController(Converter converter, SheetColumnService sheetColumnService) {
        this.converter = converter;
        this.sheetColumnService = sheetColumnService;
    }

    @GetMapping("/downloadBySheetId/{id}")
    public ResponseEntity<?> getSheetColumnBySheetId(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(sheetColumnService.getAllSheetColumnBySheetId (converter.convertToSheet(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


}



