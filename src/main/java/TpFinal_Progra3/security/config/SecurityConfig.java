package TpFinal_Progra3.security.config;

import TpFinal_Progra3.security.filters.JwtAuthenticationFilter;
import TpFinal_Progra3.security.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    //Este es el manejador de Errores que se lanzan antes del Controller
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
                // Configura las reglas de autorización para las solicitudes HTTP.
                http.authorizeHttpRequests(auth -> auth
                        //autenticacion sin restriccion
                        .requestMatchers("/auth/**","/v3/api-docs/**","/swagger-ui/**","swagger-ui.html","/proxy/**")
                                .permitAll()
                        // Otros EndPoints deben estar autenticados
//                        .anyRequest().authenticated())
                .anyRequest().permitAll())

                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                //Esto Ejecuta mei filtro personalizado para login y pass que va a retornar si es valido
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //Establece la clase encargada de manejar las excepciones que sean lanzadas.
                .exceptionHandling(e -> e.authenticationEntryPoint(restAuthenticationEntryPoint));

        // Devuelve la cadena de filtros de seguridad
        return http.build();
    }
}
