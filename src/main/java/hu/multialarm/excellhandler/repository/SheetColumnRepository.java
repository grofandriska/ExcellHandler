package hu.multialarm.excellhandler.repository;

import hu.multialarm.excellhandler.model.excel.Sheet;
import hu.multialarm.excellhandler.model.excel.SheetColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SheetColumnRepository extends JpaRepository<SheetColumn, Long> {
    List<SheetColumn> findSheetColumnsBySheet(Sheet sheet);
}
