package hu.multialarm.excelhandler.repository;

import hu.multialarm.excelhandler.model.excel.Excel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRepository extends JpaRepository<Excel, Long> {

	List<Excel> findByFileName(@NotNull String fileName);
	void deleteByFileName(@NotNull String fileName);

}
