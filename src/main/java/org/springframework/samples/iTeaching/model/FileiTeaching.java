package org.springframework.samples.iTeaching.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="files")
public class FileiTeaching extends BaseEntity{
	
	private String docName;
	private String docType;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "asignatura_id")
	private Asignatura asignatura;
	
	@Lob
	private byte[] data;

	public FileiTeaching() {
		// TODO Auto-generated constructor stub
	}
	
	public FileiTeaching(String docName, String docType, byte[] data, Asignatura asignatura) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
		this.asignatura= asignatura;
	}

}