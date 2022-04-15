package org.springframework.samples.iTeaching.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clase")
public class Clase extends BaseEntity {

	@Column(name = "horaComienzo")
	// @DateTimeFormat(pattern = "yyyy-MM-dd :HH:mm:ss")
	private String horaComienzo;

	@Column(name = "horaFin")
	// @DateTimeFormat(pattern = "yyyy-MM-dd :HH:mm:ss")
	private String horaFin;

	@Column(name = "aceptacionAlumno", columnDefinition = "boolean default false")
	// @DateTimeFormat(pattern = "yyyy-MM-dd :HH:mm:ss")
	private Boolean aceptacionAlumno;

	@Column(name = "aceptacionProfesor", columnDefinition = "boolean default false")
	// @DateTimeFormat(pattern = "yyyy-MM-dd :HH:mm:ss")
	private Boolean aceptacionProfesor;

	// @NotNull
	@Column(name = "estadoclase")
	@Enumerated(value = EnumType.STRING)
	private estadoClase estadoClase;

	@ManyToOne
	@JoinColumn(name = "alumno")
	private Alumno alumno;

	@ManyToOne
	@JoinColumn(name = "profesor")
	private Profesor profesor;

	@ManyToOne
	@JoinColumn(name = "asignatura")
	private Asignatura asignatura;

}
