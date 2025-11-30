package cl.levelupgamer.pedidoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequest {

    private ClienteDto cliente;
    private List<PedidoProductoDto> productos;
    private Integer total;
}
