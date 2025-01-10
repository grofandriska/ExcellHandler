package hu.multialarm.excelhandler.repository;

import hu.multialarm.excelhandler.model.excel.Sheet;
import hu.multialarm.excelhandler.model.excel.SheetColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetColumnRepository extends JpaRepository<SheetColumn, Long> {
    List<SheetColumn> findSheetColumnsBySheet(Sheet sheet);
}
