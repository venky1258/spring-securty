package com.mypack.Th6;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
public class config {

    // Define the in-memory user details
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("abc")
                        .password("$2a$12$VbjOyaSzVEKpWXk4oLmT7edC3qqihwvujZOHT3.sEPHJhOCASshR2")
                        .roles("USER")
                        .build();

        var user2 = User.withUsername("bbb")
                        .password("$2a$12$VbjOyaSzVEKpWXk4oLmT7edC3qqihwvujZOHT3.sEPHJhOCASshR2")
                        .roles("ADMIN")
                        .build();

        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;
    }
    
//    // Configure security to permit all requests (or restrict as needed)
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                .build();
//    }
    
    
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http.csrf()
                  .disable()
                  .authorizeRequests()
                  .requestMatchers("/rest/**")
                 .hasRole("ADMIN")
                  //.authenticated()             // Require authentication for /rest/** endpoints
                  
                  .anyRequest()
                  .permitAll()                 // Allow all other requests without authentication
                  .and()
                  .httpBasic()                 // Use HTTP Basic Authentication for authenticated requests
                  .and()
                  .build();                    // Build the SecurityFilterChain
  }



    // Password encoder - NoOp (for development purposes)
//Password encoder 
  @Bean
  public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

}
