

 /*
 #####################################################################
 
 
 Esta clase no se usa en "producción" porque afecta el resultado al 
 acceder desde cliente.
 Pero se puede implementar rápidamente para, por ejemplo, dar acceso
 al controlador de 'seguridad' acciendo por un rol específico. Se pensó
 en admin.
 
 Expone 3 formas de implementarse: 
 - Para todo el aplicativo
 - En función de un path concreto/método
 - Por roles
 
 
 
 #####################################################################
 
 
 
package com.jubiter.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


// Seguridad por roles

@SuppressWarnings("deprecation")
@Configuration
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("accionista").password("accionista").roles("accionista");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin");
	}
	
	/*
	 //Para todo el API, acceso general a ese socket
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	}
	
	
	
	
	 //Para URL, no se puede acceder si empieza por ese path
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
	}
	
	
	
	// Basado en roles (solo puede acceder "admin"
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/auth/**")
			.hasAnyRole("admin").anyRequest()
			.fullyAuthenticated().and().httpBasic();
	}
	

	
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	
}*/
