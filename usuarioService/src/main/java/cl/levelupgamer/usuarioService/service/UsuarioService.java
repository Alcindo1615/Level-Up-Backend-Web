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

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public String registrarUsuario(Usuario usuario)
            throws ExecutionException, InterruptedException {

        // 🔥 FECHA EN ESPAÑOL - CHILE
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(
                "d 'de' MMMM 'de' yyyy, HH:mm:ss",
                new Locale("es", "CL")
        );

        usuario.setCreadoEn(ahora.format(formato));

        return usuarioRepository.crearUsuario(usuario);
    }

    // READ
    public Usuario obtenerUsuarioPorId(String id)
            throws ExecutionException, InterruptedException {
        return usuarioRepository.obtenerUsuarioPorId(id);
    }

    public List<Usuario> listarUsuarios()
            throws ExecutionException, InterruptedException {
        return usuarioRepository.listarUsuarios();
    }

    // UPDATE
    public String actualizarUsuario(String id, Usuario usuario)
            throws ExecutionException, InterruptedException {

        // Opcional: actualizar fecha de modificación en español
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(
                "d 'de' MMMM 'de' yyyy, HH:mm:ss",
                new Locale("es", "CL")
        );

        usuario.setCreadoEn(ahora.format(formato));

        return usuarioRepository.actualizarUsuario(id, usuario);
    }

    // DELETE
    public String eliminarUsuario(String id)
            throws ExecutionException, InterruptedException {
        return usuarioRepository.eliminarUsuario(id);
    }
}
