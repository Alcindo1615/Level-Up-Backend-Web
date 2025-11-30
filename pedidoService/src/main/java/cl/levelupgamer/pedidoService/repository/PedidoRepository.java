package cl.levelupgamer.pedidoService.repository;

import cl.levelupgamer.pedidoService.model.ClienteDto;
import cl.levelupgamer.pedidoService.model.PedidoProductoDto;
import cl.levelupgamer.pedidoService.model.PedidoRequest;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class PedidoRepository {

    private final Firestore firestore;

    public String guardarPedido(PedidoRequest pedido) throws ExecutionException, InterruptedException {

        Map<String, Object> data = new HashMap<>();

        // Mapear cliente
        ClienteDto c = pedido.getCliente();
        Map<String, Object> clienteMap = new HashMap<>();
        clienteMap.put("nombre", c.getNombre());
        clienteMap.put("email", c.getEmail());
        clienteMap.put("telefono", c.getTelefono());
        clienteMap.put("direccion", c.getDireccion());
        data.put("cliente", clienteMap);

        // Mapear productos
        List<Map<String, Object>> productosList = new ArrayList<>();

        for (PedidoProductoDto p : pedido.getProductos()) {
            Map<String, Object> prodMap = new HashMap<>();
            prodMap.put("id", p.getId());
            prodMap.put("titulo", p.getTitulo());
            prodMap.put("descripcion", p.getDescripcion());
            prodMap.put("categoria", p.getCategoria());
            prodMap.put("cantidad", p.getCantidad());
            prodMap.put("precio", p.getPrecio());
            prodMap.put("stock", p.getStock());
            prodMap.put("imagen", p.getImagen());
            productosList.add(prodMap);
        }

        data.put("productos", productosList);
        data.put("total", pedido.getTotal());
        data.put("fechaCreacion", Date.from(Instant.now()));

        DocumentReference docRef = firestore.collection("pedidos").document();
        docRef.set(data).get();

        return docRef.getId();
    }

    // (Opcional) Método para obtener un pedido por ID más adelante
}
