package cl.levelupgamer.usuarioService.controller;

import cl.levelupgamer.usuarioService.model.Usuario;
import cl.levelupgamer.usuarioService.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controlador REST para el CRUD de Usuario.
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000") // React
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // CREATE
    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario en la colección 'usuarios' de Firestore")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al registrar el usuario")
    })
    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario)
            throws ExecutionException, InterruptedException {

        String id = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(id);
    }

    // READ: obtener uno
    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario por su ID de documento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String id)
            throws ExecutionException, InterruptedException {

        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // READ: listar todos
    @Operation(summary = "Listar usuarios", description = "Lista todos los usuarios registrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios()
            throws ExecutionException, InterruptedException {

        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // UPDATE
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable String id,
                                                     @RequestBody Usuario usuario)
            throws ExecutionException, InterruptedException {

        Usuario existente = usuarioService.obtenerUsuarioPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.actualizarUsuario(id, usuario);
        Usuario actualizado = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id)
            throws ExecutionException, InterruptedException {

        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
