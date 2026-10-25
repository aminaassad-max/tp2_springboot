package pharmacy.example.pharmacy.config;

import pharmacy.example.pharmacy.Service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {


    private final CustomUserDetailsService userDetailsService;


    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .userDetailsService(userDetailsService)

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/login").permitAll()

                .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                .requestMatchers("/medicaments/**")
                    .hasAnyRole("ADMIN", "PHARMACIEN", "CLIENT")

                .requestMatchers("/ventes/**")
                    .hasAnyRole("ADMIN", "CAISSIER")

                .requestMatchers("/css/**", "/js/**")
                    .permitAll()

                .anyRequest()
                    .authenticated()
            )


            .formLogin(form -> form

                .loginPage("/login")

                .successHandler(successHandler())

                .permitAll()
            )


            .logout(logout -> logout

                .logoutSuccessUrl("/login?logout")

                .permitAll()
            )


            .exceptionHandling(ex -> ex

                .accessDeniedPage("/access-denied")
            );


        return http.build();
    }



    @Bean
    public AuthenticationSuccessHandler successHandler() {

        return (request, response, authentication) -> {


            var roles = authentication.getAuthorities();


            if (roles.stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {

                response.sendRedirect("/admin");

            } else if (roles.stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_PHARMACIEN"))) {

                response.sendRedirect("/medicaments");

            } else if (roles.stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_CAISSIER"))) {

                response.sendRedirect("/ventes");

            } else {

                response.sendRedirect("/home");
            }
        };
    }



    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

}