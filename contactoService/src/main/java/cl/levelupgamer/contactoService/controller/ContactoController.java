package cl.levelupgamer.contactoService.controller;

import cl.levelupgamer.contactoService.model.Contacto;
import cl.levelupgamer.contactoService.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // React
public class ContactoController {

    private final ContactoService contactoService;

    // GUARDAR CONTACTO
    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Contacto contacto) {
        try {
            String id = contactoService.guardar(contacto);
            return ResponseEntity.ok("Solicitud guardada con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al guardar: " + e.getMessage());
        }
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable String id) {
        try {
            Contacto c = contactoService.buscarPorId(id);
            if (c == null) {
                return ResponseEntity.status(404).body("No encontrado");
            }
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al buscar: " + e.getMessage());
        }
    }
}
