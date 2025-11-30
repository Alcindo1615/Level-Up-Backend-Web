package cl.levelupgamer.contactoService.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws Exception {

        // Solo inicializamos Firebase una vez
        if (FirebaseApp.getApps().isEmpty()) {

            // serviceAccountKey.json debe estar en src/main/resources
            try (InputStream serviceAccount =
                         new ClassPathResource("serviceAccountKey.json").getInputStream()) {

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setProjectId("level-up-web-4664d") // tu PROJECT ID
                        .build();

                FirebaseApp.initializeApp(options);
            }
        }

        // Devuelve el cliente Firestore que usará todo el proyecto
        return FirestoreClient.getFirestore();
    }
}
