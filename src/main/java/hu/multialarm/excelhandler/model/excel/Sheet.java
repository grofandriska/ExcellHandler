package hu.multialarm.excelhandler.model.excel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class Sheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "excel_id")
	private Excel excel;
	
	@NotNull
	private String sheetName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Excel getExcel() {
		return excel;
	}

	public void setExcel(Excel excel) {
		this.excel = excel;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
