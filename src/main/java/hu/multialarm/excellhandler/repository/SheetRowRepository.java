package hu.multialarm.excellhandler.repository;

import hu.multialarm.excellhandler.model.excell.SheetRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRowRepository extends JpaRepository<SheetRow,Long> {
}
