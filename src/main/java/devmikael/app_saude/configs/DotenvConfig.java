package devmikael.app_saude.configs;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    Dotenv dotenv() {
        return Dotenv.load(); 
    }
}
