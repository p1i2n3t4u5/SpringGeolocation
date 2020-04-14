package com.geo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.geo.security.LogoutSuccessHandler;
import com.geo.security.RestUnauthorizedEntryPoint;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	public static final String REMEMBER_ME_KEY = "rememberme_key";

	public SecurityConfiguration() {
		super();
		logger.info("loading SecurityConfig ................................................ ");
	}

	@Autowired
	private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AccessDeniedHandler restAccessDeniedHandler;

	@Autowired
	private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler restAuthenticationFailureHandler;

	@Autowired
	private RememberMeServices rememberMeServices;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
		        .antMatchers( "/public/**").permitAll()  
		        .antMatchers("/user/**").hasAnyAuthority("admin", "user")
		        .antMatchers("/role/**").hasAnyAuthority("admin").anyRequest().authenticated()
		        .antMatchers("/**").authenticated()
				.and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
				.accessDeniedHandler(restAccessDeniedHandler).and().formLogin()
				.loginPage("/login.html") 
				.failureUrl("/login-error.html")
				.loginProcessingUrl("/login")
				.successHandler(restAuthenticationSuccessHandler).failureHandler(restAuthenticationFailureHandler)
				.usernameParameter("username").passwordParameter("password").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID").permitAll()
				.and().rememberMe().rememberMeServices(rememberMeServices).rememberMeParameter("remember-me")
				.rememberMeCookieName("remember-me").key(REMEMBER_ME_KEY);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html", "/partials/**", "/template/**", "/",
				"/error/**", "/h2-console", "*/h2-console/*","/swagger-ui.html/*","/public/style.css");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.applyPermitDefaultValues();
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		config.addAllowedMethod(HttpMethod.PATCH);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.TRACE);
		config.setAllowCredentials(true);// this line is important it sends only specified domain instead of *
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}


}