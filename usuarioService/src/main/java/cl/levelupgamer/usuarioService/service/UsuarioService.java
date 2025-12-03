// src/main/java/cl/levelupgamer/usuarioService/service/UsuarioService.java
package cl.levelupgamer.usuarioService.service;

import cl.levelupgamer.usuarioService.model.Usuario;
import cl.levelupgamer.usuarioService.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Inyección de dependencia del repositorio
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * CREATE - Registrar usuario en Firestore.
     * Devuelve el ID del documento creado.
     */
    public String registrarUsuario(Usuario usuario)
            throws ExecutionException, InterruptedException {

        // Fecha de creación en español (Chile)
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(
                "dd 'de' MMMM 'de' yyyy HH:mm",
                new Locale("es", "CL")
        );

        usuario.setCreadoEn(ahora.format(formato));

        return usuarioRepository.registrarUsuario(usuario);
    }

    /**
     * READ - Obtener un usuario por ID (documento Firestore).
     */
    public Usuario obtenerUsuarioPorId(String id)
            throws ExecutionException, InterruptedException {

        return usuarioRepository.obtenerUsuarioPorId(id);
    }

    /**
     * READ - Listar todos los usuarios.
     */
    public List<Usuario> listarUsuarios()
            throws ExecutionException, InterruptedException {

        return usuarioRepository.listarUsuarios();
    }

    /**
     * UPDATE - Actualizar un usuario existente.
     */
    public String actualizarUsuario(String id, Usuario usuario)
            throws ExecutionException, InterruptedException {

        // Opcional: actualizar la fecha de modificación
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(
                "dd 'de' MMMM 'de' yyyy HH:mm",
                new Locale("es", "CL")
        );

        usuario.setCreadoEn(ahora.format(formato));

        return usuarioRepository.actualizarUsuario(id, usuario);
    }

    /**
     * DELETE - Eliminar un usuario por ID.
     */
    public String eliminarUsuario(String id)
            throws ExecutionException, InterruptedException {

        return usuarioRepository.eliminarUsuario(id);
    }
}
