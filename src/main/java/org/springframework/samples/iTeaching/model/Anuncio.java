package org.springframework.samples.iTeaching.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="anuncio")
public class Anuncio extends BaseEntity {

	
	@Column(name="titulo")
	@NotEmpty
	String titulo;
	
	@Column(name="descripcion")
	@NotEmpty 
	String descripcion;
	
	@Column(name="asignatura")
	@NotEmpty
	String asignatura;
	
	@Column(name="precio")
	@NotNull
	Double precio;
	
	@ManyToOne
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;
	
	@ManyToMany
	private List<Alumno> alumnos;
}
