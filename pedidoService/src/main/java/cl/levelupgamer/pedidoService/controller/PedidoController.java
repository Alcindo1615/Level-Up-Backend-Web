package cl.levelupgamer.pedidoService.controller;

import cl.levelupgamer.pedidoService.model.PedidoRequest;
import cl.levelupgamer.pedidoService.model.PedidoResponse;
import cl.levelupgamer.pedidoService.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000") // React
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(@RequestBody PedidoRequest pedidoRequest) {

        try {
            String idPedido = pedidoService.crearPedido(pedidoRequest);

            PedidoResponse response = PedidoResponse.builder()
                    .idPedido(idPedido)
                    .mensaje("Pedido creado correctamente")
                    .build();

            return ResponseEntity.ok(response);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
