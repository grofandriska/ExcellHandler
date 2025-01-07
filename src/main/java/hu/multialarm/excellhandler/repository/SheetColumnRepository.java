package hu.multialarm.excellhandler.repository;

import hu.multialarm.excellhandler.model.excell.SheetColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetColumnRepository extends JpaRepository<SheetColumn, Long> {
}
