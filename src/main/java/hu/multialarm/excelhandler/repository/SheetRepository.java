package hu.multialarm.excelhandler.repository;

import hu.multialarm.excelhandler.model.excel.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends JpaRepository<Sheet,Long> {
}
