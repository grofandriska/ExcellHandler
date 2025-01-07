package hu.multialarm.excellhandler.model.excell;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SheetColumn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer orderNumber;
	
	@NotNull
	private String columnName;
	
	@NotNull
	private String columnType;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "sheet_id", nullable = false)
	private Sheet sheet;
}
