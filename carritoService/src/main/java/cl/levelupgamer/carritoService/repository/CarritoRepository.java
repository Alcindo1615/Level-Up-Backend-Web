package cl.levelupgamer.carritoService.repository;

import cl.levelupgamer.carritoService.model.CarritoItemRequest;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class CarritoRepository {

    private final Firestore firestore;

    public List<String> guardarCarrito(List<CarritoItemRequest> items)
            throws ExecutionException, InterruptedException {

        List<String> ids = new ArrayList<>();

        for (CarritoItemRequest item : items) {

            Map<String, Object> data = new HashMap<>();
            data.put("idProducto", item.getIdProducto());
            data.put("titulo", item.getTitulo());
            data.put("categoria", item.getCategoria());
            data.put("cantidad", item.getCantidad());
            data.put("precio", item.getPrecio());  // 👈 Integer OK
            data.put("estado", "en_carrito");
            data.put("fechaCreacion", Date.from(Instant.now()));

            DocumentReference docRef = firestore
                    .collection("carrito")
                    .document();

            docRef.set(data).get();
            ids.add(docRef.getId());
        }

        return ids;
    }
}
