package org.jboss.perf.test.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="GLOBALTB")
public class Global {
	private Long id;
		
	private Threshold threshold;
	
	public Global() {
	}
	
	public Global(Threshold threshold) {
		this.threshold = threshold;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="threshold_id")
	public Threshold getThreshold() {
		return threshold;
	}
	
	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}
}
