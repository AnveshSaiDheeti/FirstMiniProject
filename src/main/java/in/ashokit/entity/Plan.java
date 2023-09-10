package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Integer categoryId;
	
	private String ActiveSW;
	
	
	private String CreatedBy;
	private String UpdatedBy;
	
	@CreationTimestamp 
	@Column(name="created_Date", updatable= false)
	private LocalDate createdDate; 
	
	@UpdateTimestamp
	@Column(name="updated_Date", insertable= false)
	private LocalDate UpdatedDate;

}
