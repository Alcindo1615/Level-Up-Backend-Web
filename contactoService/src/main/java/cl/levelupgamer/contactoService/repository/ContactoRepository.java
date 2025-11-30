package cl.levelupgamer.contactoService.repository;

import cl.levelupgamer.contactoService.model.Contacto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ExecutionException;

@Repository
public class ContactoRepository {

    private final Firestore firestore;

    public ContactoRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    // GUARDAR
    public String guardarContacto(Contacto contacto) throws ExecutionException, InterruptedException {
        CollectionReference contactos = firestore.collection("contacto");
        DocumentReference nuevoDoc = contactos.document(); // genera ID automático
        contacto.setId(nuevoDoc.getId()); // guardamos el ID en el modelo
        ApiFuture<WriteResult> future = nuevoDoc.set(contacto);
        future.get(); // esperar a que se complete
        return contacto.getId();
    }

    // BUSCAR POR ID
    public Contacto obtenerPorId(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("contacto").document(id);
        DocumentSnapshot snapshot = docRef.get().get();

        if (snapshot.exists()) {
            return snapshot.toObject(Contacto.class);
        }
        return null;
    }
}
