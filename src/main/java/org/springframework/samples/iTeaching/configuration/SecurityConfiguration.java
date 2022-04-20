package org.springframework.samples.iTeaching.configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.iTeaching.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	UserDetailsService userDetailsService;
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/ofertas/misOfertas").hasAuthority("profesor")
				.antMatchers("/salas/new").hasAnyAuthority("profesor")
				.antMatchers("/pay").hasAnyAuthority("alumno")
				.antMatchers("/chat/**").hasAnyAuthority("alumno","profesor")
				.antMatchers("/pagar").hasAnyAuthority("alumno")
				.antMatchers("/salas").hasAnyAuthority("profesor", "alumno")
				.antMatchers("/ofertas/find/**").hasAnyAuthority("alumno")
				.antMatchers("/alumnos/new").permitAll()
				.antMatchers("/logging", "/actuator/**").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/logged").authenticated()
				.antMatchers("/asignatura/**").authenticated()
				.antMatchers("/asignaturas/**").authenticated()
				.and()
				.rememberMe().key("ABCdefglo9010221234")
				.and()
				 	.formLogin()
				 	.loginPage("/login")
				 	.defaultSuccessUrl("/logged")
					.permitAll()
					.and()
					.logout().permitAll()
					.and().csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository());
                // Configuración para que funcione la consola de administración
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().disable();
				// http.csrf().ignoringAntMatchers("/h2-console/**");
				// http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")
	      .passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}

}
