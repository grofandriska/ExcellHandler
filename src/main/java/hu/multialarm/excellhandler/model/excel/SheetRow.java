package hu.multialarm.excellhandler.model.excel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
	private SheetColumn sheetColumnId;
	
	@NotNull
	private String valueText;


}
