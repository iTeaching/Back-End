package org.springframework.samples.iTeaching.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profesor")
public class Profesor extends Person{

	@Column(name="puntuacion")
	private Double puntuacion;
	
	@Column(name="division")
	private Integer division;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor")
	private Set<Asignatura> asignaturas;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Valoracion> valoraciones;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
}
