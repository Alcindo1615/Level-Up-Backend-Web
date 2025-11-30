package cl.levelupgamer.productoService.repository;

import cl.levelupgamer.productoService.model.Producto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ProductoRepository {

    private static final String COLLECTION_NAME = "productos";

    public ProductoRepository() { }

    // Obtenemos Firestore siempre desde FirestoreClient (NO se cierra)
    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    public String save(Producto producto) throws ExecutionException, InterruptedException {
        Firestore firestore = getFirestore();
        CollectionReference collection = firestore.collection(COLLECTION_NAME);

        DocumentReference docRef;
        if (producto.getId() != null && !producto.getId().isEmpty()) {
            docRef = collection.document(producto.getId());
        } else {
            docRef = collection.document();
            producto.setId(docRef.getId());
        }

        ApiFuture<WriteResult> future = docRef.set(producto);
        future.get();

        return producto.getId();
    }

    public List<Producto> findAll() throws ExecutionException, InterruptedException {
        Firestore firestore = getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Producto> productos = new ArrayList<>();

        for (DocumentSnapshot doc : documents) {
            try {
                Producto producto = mapDocumentoAProducto(doc);
                productos.add(producto);
            } catch (Exception e) {
                System.err.println("Error al convertir documento " + doc.getId()
                        + " a Producto: " + e.getMessage());
            }
        }

        return productos;
    }

    // 🔹 NUEVO: obtener producto por id
    public Producto findById(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = getFirestore();
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        DocumentSnapshot doc = docRef.get().get();

        if (!doc.exists()) {
            return null;
        }

        return mapDocumentoAProducto(doc);
    }

    private Producto mapDocumentoAProducto(DocumentSnapshot doc) {
        String id = doc.getId();
        String categoria = doc.getString("categoria");
        String descripcion = doc.getString("descripcion");
        String imagen = doc.getString("imagen");
        String titulo = doc.getString("titulo");

        Integer precio = null;
        Integer stock = null;

        Object precioObj = doc.get("precio");
        if (precioObj instanceof Number) {
            precio = ((Number) precioObj).intValue();
        } else if (precioObj instanceof String) {
            try {
                precio = Integer.parseInt((String) precioObj);
            } catch (NumberFormatException ignored) {}
        }

        Object stockObj = doc.get("stock");
        if (stockObj instanceof Number) {
            stock = ((Number) stockObj).intValue();
        } else if (stockObj instanceof String) {
            try {
                stock = Integer.parseInt((String) stockObj);
            } catch (NumberFormatException ignored) {}
        }

        return new Producto(id, categoria, descripcion, imagen, precio, stock, titulo);
    }
}
