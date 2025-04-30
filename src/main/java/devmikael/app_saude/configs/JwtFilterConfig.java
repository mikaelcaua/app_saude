package devmikael.app_saude.configs;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import devmikael.app_saude.security.JwtAuthFilter;

@Configuration
public class JwtFilterConfig {

    @Bean
    FilterRegistrationBean<JwtAuthFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtAuthFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
