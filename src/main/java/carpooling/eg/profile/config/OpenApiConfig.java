package carpooling.eg.profile.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI profileOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Profile Management API")
                        .description("API for managing user profiles in the carpooling application")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Carpooling Team")
                                .email("support@carpooling.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
} 