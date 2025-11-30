package cl.levelupgamer.usuarioService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de Usuario para la colección "usuarios" en Firestore.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    // Se guardará el id del documento de Firestore
    private String id;

    private String rut;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String password;

    // Fecha de creación en texto (por simplicidad)
    private String creadoEn;
}
