package com.jobJunior.cursomc.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.jobJunior.cursomc.security.JWTAuthenticationFilter;
import com.jobJunior.cursomc.security.JWTAuthorizationFilter;
import com.jobJunior.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private Environment environment;

	@Autowired
	private UserDetailsService userDetailsService;

	public static final String[] PUBLIC_MATCHERS = { "/h2-console/**","/error","/" };
	public static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/estados/**", "/categorias/**" };
	public static final String[] PUBLIC_MATCHERS_POST = { "/clientes/", "/auth/forgot/**" };

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {

		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			httpSecurity.headers().frameOptions().disable();
		}

		httpSecurity.cors().and().csrf().disable();
		httpSecurity.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().anyRequest().authenticated();

		httpSecurity.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		httpSecurity.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	public void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	 @Bean // TODO Apenas para testes, remover em produção
	  public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    // Don't do this in production, use a proper list of allowed origins
	    // TODO trocar o endereço pelo do servidor de produção
	    config.setAllowedOrigins(Collections.singletonList("http://localhost:8100"));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	  }
	
	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST","PUT","GET","DELETE","OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
