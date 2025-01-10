package hu.multialarm.excelhandler.model.excel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SheetRow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer rowOrderNumber;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "sheetColumn_id", nullable = false)
	private SheetColumn sheetColumn;

	@NotNull
	private String valueText;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRowOrderNumber() {
		return rowOrderNumber;
	}

	public void setRowOrderNumber(Integer rowOrderNumber) {
		this.rowOrderNumber = rowOrderNumber;
	}

	public SheetColumn getSheetColumn() {
		return sheetColumn;
	}

	public void setSheetColumn(SheetColumn sheetColumn) {
		this.sheetColumn = sheetColumn;
	}

	public String getValueText() {
		return valueText;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
}
