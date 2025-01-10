package hu.multialarm.excellhandler.services;

import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.model.excel.SheetRow;
import hu.multialarm.excellhandler.repository.SheetRepository;
import hu.multialarm.excellhandler.repository.SheetRowRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
public class SheetRowService {

    private SheetRowRepository sheetRowRepository;

    public SheetRowService(SheetRowRepository sheetRowRepository) {
        this.sheetRowRepository = sheetRowRepository;
    }

    public SheetRow save(SheetRow sheetRow){
        return sheetRowRepository.save(sheetRow);
    }
    private SheetRow createAndSaveSheetRow(SheetRow sheetRow){

        return sheetRowRepository.save(sheetRow);

    }


}
