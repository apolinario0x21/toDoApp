package apolinario0x21.toDoApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Project - ToDoApp")
                                .description("Documentação da Rest API do ToDoApp")
                                .version("1.0.0")
                                .termsOfService("http://swagger.io/terms/")
                                .contact(new io.swagger.v3.oas.models.info.Contact()
                                        .name("apolinario0x21")
                                        .url("https://apolinario0x21.github.io/my/"))
                );
    }
}
