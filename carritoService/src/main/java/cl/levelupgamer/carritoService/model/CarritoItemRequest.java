package cl.levelupgamer.carritoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItemRequest {

    private String idProducto;
    private String titulo;
    private String categoria;
    private Integer cantidad;
    private Integer precio;   // 👈 ahora es Integer
}
