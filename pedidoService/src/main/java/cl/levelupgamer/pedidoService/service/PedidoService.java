package cl.levelupgamer.pedidoService.service;

import cl.levelupgamer.pedidoService.model.PedidoRequest;
import cl.levelupgamer.pedidoService.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public String crearPedido(PedidoRequest pedidoRequest)
            throws ExecutionException, InterruptedException {

        return pedidoRepository.guardarPedido(pedidoRequest);
    }
}
