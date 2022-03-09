package org.springframework.samples.iTeaching.model;

import java.util.Set;

import javax.persistence.CascadeType;
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

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
	private Set<Anuncio> anuncios;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "id")
	private Set<Anuncio> alumnos;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
}
