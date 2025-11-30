package cl.levelupgamer.contactoService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración base de OpenAPI/Swagger para el microservicio de Usuario.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Usuario – Level-Up Gamer",
                version = "1.0",
                description = "Endpoints para gestionar Contacto en Firestore"
        )
)
public class OpenApiConfig {
    // No hace falta código adentro, solo las anotaciones
}
