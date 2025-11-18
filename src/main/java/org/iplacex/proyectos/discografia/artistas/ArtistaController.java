package org.iplacex.proyectos.discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    // POST /api/artista
    @PostMapping(
            value = "/artista",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> crearArtista(@RequestBody Artista artista) {
        Artista creado = artistaRepository.save(artista);
        return ResponseEntity.status(201).body(creado);
    }

    // GET /api/artistas
    @GetMapping(
            value = "/artistas",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> obtenerTodos() {
        List<Artista> lista = artistaRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET /api/artista/{id}
    @GetMapping(
            value = "/artista/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> obtenerPorId(@PathVariable("id") String id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        return artista.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Artista no encontrado"));
    }

    // PUT /api/artista/{id}
    @PutMapping(
            value = "/artista/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizarArtista(@PathVariable("id") String id,
                                               @RequestBody Artista artistaBody) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Artista no encontrado para actualizar");
        }

        artistaBody._id = id;
        Artista actualizado = artistaRepository.save(artistaBody);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/artista/{id}
    @DeleteMapping(
            value = "/artista/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> eliminarArtista(@PathVariable("id") String id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Artista no encontrado para eliminar");
        }

        artistaRepository.deleteById(id);
        return ResponseEntity.ok("Artista eliminado correctamente");
    }
}
