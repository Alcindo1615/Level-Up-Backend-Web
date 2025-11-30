package cl.levelupgamer.productoService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private String id;      
    private String categoria;
    private String descripcion;
    private String imagen;
    private Integer precio;
    private Integer stock;
    private String titulo;
}
