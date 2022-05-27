package org.springframework.samples.iTeaching.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity {

	@Column(name = "first_name")
	@NotEmpty
	protected String firstName;

	@Column(name = "last_name")
	@NotEmpty
	protected String lastName;
	
	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@Column(name= "email")
	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
	private String email;
	public String getFirstName() {
		return this.firstName;
	}
	@Column (name= "premium")
	@NotEmpty
	private boolean premium;

	@Transient
	public String getFullName() {
		return firstName+" "+lastName;
	}

}
