// src/main/java/cl/levelupgamer/usuarioService/config/FirebaseConfig.java
package cl.levelupgamer.usuarioService.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {

    // Se ejecuta automáticamente al iniciar la aplicación
    @PostConstruct
    public void init() throws Exception {
        // Ruta al JSON de la cuenta de servicio de Firebase
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/serviceAccountKey.json");

        // Configuración de Firebase con las credenciales y el Project ID
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("level-up-web-4664d") // Reemplaza si tu projectId es otro
                .build();

        // Evita inicializar Firebase más de una vez
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}
