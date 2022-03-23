package org.springframework.samples.iTeaching.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sala")
public class Sala extends BaseEntity{
	
	
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "id")
//	private List<Alumno> alumnos;
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "sala_alumno",
            joinColumns = @JoinColumn(name = "sala_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
	List<Alumno> alumnos;
	
	@Column(name="nombre")
	@NotEmpty
	String nombre;

	@Column(name="url")
	String url;

	@ManyToOne
	@JoinColumn(name = "profesor")
	private Profesor profesor;
}
