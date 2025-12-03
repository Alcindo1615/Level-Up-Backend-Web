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
 * Repositorio que maneja directamente las operaciones CRUD contra Firestore.
 * La colección usada es "usuarios".
 */
@Repository
public class UsuarioRepository {

    private static final String COLLECTION_NAME = "usuarios";

    /**
     * CREATE – Registrar usuario.
     * Firestore genera un ID automático y lo devolvemos.
     */
    public String registrarUsuario(Usuario usuario) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        // Creamos un nuevo documento con ID automático
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();

        // Guardamos el usuario en Firestore
        ApiFuture<WriteResult> future = docRef.set(usuario);
        future.get(); // esperamos a que termine

        return docRef.getId(); // devolvemos el ID generado
    }

    /**
     * READ – Obtener usuario por ID (documento Firestore).
     */
    public Usuario obtenerUsuarioPorId(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            return null; // si no existe, retornamos null
        }

        Usuario usuario = document.toObject(Usuario.class);
        usuario.setId(document.getId()); // aseguramos que el ID quede seteado

        return usuario;
    }

    /**
     * READ – Listar todos los usuarios.
     */
    public List<Usuario> listarUsuarios() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documentos = future.get().getDocuments();

        List<Usuario> lista = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documentos) {
            Usuario usuario = doc.toObject(Usuario.class);
            usuario.setId(doc.getId()); // asignamos el id
            lista.add(usuario);
        }

        return lista;
    }

    /**
     * UPDATE – Actualizar usuario por ID.
     */
    public String actualizarUsuario(String id, Usuario usuario) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);

        // Reemplazamos el documento completo con los nuevos datos
        ApiFuture<WriteResult> writeResult = docRef.set(usuario);

        writeResult.get(); // esperamos a que termine

        return "Usuario actualizado con éxito";
    }

    /**
     * DELETE – Eliminar usuario por ID.
     */
    public String eliminarUsuario(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> writeResult =
                db.collection(COLLECTION_NAME).document(id).delete();

        writeResult.get(); // esperamos a que termine

        return "Usuario eliminado con éxito";
    }
}
