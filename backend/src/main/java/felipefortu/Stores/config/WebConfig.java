package felipefortu.Stores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v1/produto/**") // Mapeamento para o endpoint de produto
                        .allowedOrigins("http://localhost:3000") // Origem do front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                        .allowCredentials(true); // Permitir credenciais

                registry.addMapping("/v1/venda/**") // Mapeamento para o endpoint de venda
                        .allowedOrigins("http://localhost:3000") // Origem do front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                        .allowCredentials(true); // Permitir credenciais
            }
        };
    }
}
