package org.springframework.samples.iTeaching.model;

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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="asignatura")
public class Asignatura extends BaseEntity{
	
	@Column(name="nombre")
	@NotEmpty
	String nombre;

	@Column(name="url")
	String url;
	
	@Column(name="titulo_anuncio")
	@NotEmpty
	String titulo_anuncio;
	
	@Column(name="descripcion")
	@NotEmpty 
	String descripcion;
	
	@Column(name="precio")
	@NotNull
	Double precio;
	
	@ManyToMany(cascade = {
            CascadeType.ALL
//            CascadeType.PERSIST,
//            CascadeType.MERGE
    })
    @JoinTable(name = "asignatura_alumno",
            joinColumns = @JoinColumn(name = "sala_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
	Set<Alumno> alumnos;

	@ManyToOne
	@JoinColumn(name = "profesor")
	private Profesor profesor;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Valoracion> valoraciones;
}
