package hu.multialarm.excellhandler.repository;

import hu.multialarm.excellhandler.model.excel.SheetRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRowRepository extends JpaRepository<SheetRow,Long> {
}
