package in.ashokit.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@javax.persistence.Entity
@Data
public class PlanCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryName;
	
	private String ActiveSW;
	
	private String createdBy;
	private String updatedBy;
	
	@CreationTimestamp 
	@Column(name="created_Date", updatable= false)
	private LocalDate createdDate; 
	
	@UpdateTimestamp
	@Column(name="updated_Date", insertable= false)
	private LocalDate updatedDate;

}
