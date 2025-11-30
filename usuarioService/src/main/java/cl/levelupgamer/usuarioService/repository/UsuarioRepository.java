package cl.levelupgamer.usuarioService.repository;

import cl.levelupgamer.usuarioService.model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Repositorio para acceder a la colección "usuarios" en Firestore.
 */
@Repository
public class UsuarioRepository {

    private static final String COLLECTION_NAME = "usuarios";

    private Firestore getDb() {
        return FirestoreClient.getFirestore();
    }

    /**
     * CREA un usuario en Firestore y devuelve el id del documento.
     * Además, guarda ese id dentro del campo "id" del propio documento.
     */
    public String crearUsuario(Usuario usuario) throws ExecutionException, InterruptedException {
        Firestore db = getDb();

        // Creamos un documento vacío para obtener el ID
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();

        // Asignamos el id del documento al campo id del usuario
        usuario.setId(docRef.getId());

        // Guardamos el usuario
        ApiFuture<WriteResult> writeResult = docRef.set(usuario);
        writeResult.get(); // esperamos a que termine (por si quieres manejar errores)

        return docRef.getId();
    }

    /**
     * OBTIENE un usuario por su id de documento.
     */
    public Usuario obtenerUsuarioPorId(String id) throws ExecutionException, InterruptedException {
        Firestore db = getDb();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            return null;
        }

        return document.toObject(Usuario.class);
    }

    /**
     * LISTA todos los usuarios de la colección.
     */
    public List<Usuario> listarUsuarios() throws ExecutionException, InterruptedException {
        Firestore db = getDb();

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Usuario> usuarios = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            Usuario u = doc.toObject(Usuario.class);
            usuarios.add(u);
        }

        return usuarios;
    }

    /**
     * ACTUALIZA un usuario existente por id.
     * Se asegura de que el campo usuario.id coincida con el id del documento.
     */
    public String actualizarUsuario(String id, Usuario usuario)
            throws ExecutionException, InterruptedException {

        Firestore db = getDb();

        // Aseguramos consistencia
        usuario.setId(id);

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> writeResult = docRef.set(usuario, SetOptions.merge());
        writeResult.get();

        return id;
    }

    /**
     * ELIMINA un usuario por id.
     */
    public String eliminarUsuario(String id) throws ExecutionException, InterruptedException {
        Firestore db = getDb();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> writeResult = docRef.delete();
        writeResult.get();

        return id;
    }
}
