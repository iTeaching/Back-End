package org.springframework.samples.iTeaching.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name="salas")
public class Sala extends BaseEntity{
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "id")
	private Set<Alumno> alumnos;
	
	@ManyToOne
	@JoinColumn(name = "profesor")
	private Profesor profesor;
	
	@Column(name="nombre")
	@NotEmpty
	String nombre;

}