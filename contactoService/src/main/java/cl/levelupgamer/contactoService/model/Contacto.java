package cl.levelupgamer.contactoService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {

    private String id;               // ← ID del documento Firestore
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String tipoSolicitud;
    private String categoriaProducto;
    private String mensaje;
    private String creadoEn;         // Fecha en español
}
