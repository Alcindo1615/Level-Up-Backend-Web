package cl.levelupgamer.productoService.controller;

import cl.levelupgamer.productoService.model.Producto;
import cl.levelupgamer.productoService.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Guarda un nuevo producto en Firestore"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                    content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            String id = productoService.guardarProducto(producto);
            producto.setId(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el producto: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener todos los productos",
            description = "Retorna todos los productos almacenados en Firestore"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente",
                    content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Producto> productos = productoService.obtenerTodos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener productos: " + e.getMessage());
        }
    }

    // 🔹 NUEVO: obtener producto por ID
    @Operation(
            summary = "Obtener un producto por ID",
            description = "Retorna un producto específico almacenado en Firestore según su ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente",
                    content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        try {
            Producto producto = productoService.obtenerPorId(id);

            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no encontrado con id: " + id);
            }

            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el producto: " + e.getMessage());
        }
    }
}
