package sale_manager.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired private UserDetailsServiceImpl userDetailsService;
	@Autowired private JwtAuthenticationFilter jwtAuthFil;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.cors()
			.and()
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/taikhoan/dangnhap", "/api/refreshtoken").permitAll()
					.requestMatchers("/taikhoan/admin/**").hasRole("ADMIN")
					.requestMatchers("/taikhoan/manager/**").hasRole("MANAGER")
					.requestMatchers("/taikhoan/customer/**").hasRole("CUSTOMER")
					.requestMatchers("/taikhoan/sale/**").hasRole("SALE")
					.requestMatchers("/taikhoan/warehouse/**").hasRole("WAREHOUSE")
					.requestMatchers("/taikhoan/accountant/**").hasRole("ACCOUNTANT")
					.anyRequest().authenticated()
			)
			.addFilterBefore(jwtAuthFil, UsernamePasswordAuthenticationFilter.class);
			return http.build();
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and().build();
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
