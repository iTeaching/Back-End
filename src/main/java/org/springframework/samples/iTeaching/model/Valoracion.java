package org.springframework.samples.iTeaching.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="valoracion")
public class Valoracion extends BaseEntity {

	@Column(name="puntuacion")
	@NotNull
	Double puntuacion;
	
	@Column(name="comentario")
	@NotBlank
	String comentario;
	
	@ManyToOne
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;
	
	@ManyToOne
	@JoinColumn(name = "anuncio_id")
	private Anuncio anuncio;
	
	@ManyToOne
	@JoinColumn(name = "alumno_id")
	private Alumno alumno;
	
}
