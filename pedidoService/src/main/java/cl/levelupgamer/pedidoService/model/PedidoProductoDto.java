package cl.levelupgamer.pedidoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProductoDto {

    private String id;          // id del producto
    private String titulo;
    private String descripcion;
    private String categoria;
    private Integer cantidad;
    private Integer precio;
    private Integer stock;
    private String imagen;
}
