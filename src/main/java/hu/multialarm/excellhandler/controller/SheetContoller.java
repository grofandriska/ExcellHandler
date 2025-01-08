package hu.multialarm.excellhandler.controller;

import hu.multialarm.excellhandler.services.SheetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sheet")
public class SheetContoller {

    private SheetService sheetService;

    public SheetContoller(SheetService sheetService) {
        this.sheetService = sheetService;
    }
}
