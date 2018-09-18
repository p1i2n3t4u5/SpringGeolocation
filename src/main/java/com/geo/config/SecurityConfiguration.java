package com.geo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

import com.geo.security.LogoutSuccessHandler;
import com.geo.security.RestUnauthorizedEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = { "com.geo.security" })
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
		http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/user/**").hasAnyAuthority("admin","user")
        .anyRequest().authenticated()
        .antMatchers("/role/**").hasAnyAuthority("admin")

        .and()
      .exceptionHandling()
           //.authenticationEntryPoint(restAuthenticationEntryPoint)
           .accessDeniedHandler(restAccessDeniedHandler)
           .and()
       .formLogin()
           //.loginPage("/login")  //by putting this or by applying authentication 
                                  //entrypoint default login page would not appear
           //.loginProcessingUrl("/authenticate")
           .successHandler(restAuthenticationSuccessHandler)
           .failureHandler(restAuthenticationFailureHandler)
           .usernameParameter("username")
           .passwordParameter("password")
           .permitAll()
           .and()
       .logout()
           .logoutUrl("/logout")
           .logoutSuccessHandler(logoutSuccessHandler)
           .deleteCookies("JSESSIONID")
           .permitAll()
           .and()
       .rememberMe()
           .rememberMeServices(rememberMeServices)
           .rememberMeParameter("remember-me")
           .rememberMeCookieName("remember-me")
           .key(REMEMBER_ME_KEY)
           .and()
           ;
	}

	 @Bean
	public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html", "/partials/**", "/template/**", "/",
				"/error/**");
	}

}