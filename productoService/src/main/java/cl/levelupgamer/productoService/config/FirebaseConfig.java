package cl.levelupgamer.productoService.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Configura Firebase para el microservicio de Producto.
 * Solo inicializa FirebaseApp UNA VEZ.
 */
@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initFirebase() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new ClassPathResource("serviceAccountKey.json").getInputStream()
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    // ⚠️ Usa tu PROJECT ID de Firebase
                    .setProjectId("level-up-web-4664d")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("✅ Firebase inicializado en ProductoService");
        } else {
            System.out.println("⚠️ Firebase ya estaba inicializado (ProductoService)");
        }
    }
}
