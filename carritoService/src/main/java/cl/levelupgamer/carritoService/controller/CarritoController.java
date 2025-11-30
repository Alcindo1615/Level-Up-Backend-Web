package cl.levelupgamer.carritoService.controller;

import cl.levelupgamer.carritoService.model.CarritoItemRequest;
import cl.levelupgamer.carritoService.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<List<String>> guardarCarrito(
            @RequestBody List<CarritoItemRequest> items) {

        try {
            List<String> ids = carritoService.guardarCarrito(items);
            return ResponseEntity.ok(ids);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
