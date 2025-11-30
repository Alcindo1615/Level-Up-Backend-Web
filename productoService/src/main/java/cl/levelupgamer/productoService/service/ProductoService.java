package cl.levelupgamer.productoService.service;

import cl.levelupgamer.productoService.model.Producto;
import cl.levelupgamer.productoService.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public String guardarProducto(Producto producto) throws ExecutionException, InterruptedException {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() throws ExecutionException, InterruptedException {
        return productoRepository.findAll();
    }

    // 🔹 NUEVO
    public Producto obtenerPorId(String id) throws ExecutionException, InterruptedException {
        return productoRepository.findById(id);
    }
}
