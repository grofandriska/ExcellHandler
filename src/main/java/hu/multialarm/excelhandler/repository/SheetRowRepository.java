package hu.multialarm.excelhandler.repository;

import hu.multialarm.excelhandler.model.excel.SheetRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRowRepository extends JpaRepository<SheetRow,Long> {
}
