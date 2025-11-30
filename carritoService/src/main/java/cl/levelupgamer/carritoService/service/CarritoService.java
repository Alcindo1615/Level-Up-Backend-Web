package cl.levelupgamer.carritoService.service;

import cl.levelupgamer.carritoService.model.CarritoItemRequest;
import cl.levelupgamer.carritoService.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public List<String> guardarCarrito(List<CarritoItemRequest> items)
            throws ExecutionException, InterruptedException {

        return carritoRepository.guardarCarrito(items);
    }
}
