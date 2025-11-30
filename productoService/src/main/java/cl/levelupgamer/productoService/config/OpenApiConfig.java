package cl.levelupgamer.productoService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Productos - Level-Up Gamer",
                version = "1.0",
                description = "Microservicio para gestionar los productos de la tienda Level-Up Gamer usando Firestore"
        )
)
public class OpenApiConfig {
}
