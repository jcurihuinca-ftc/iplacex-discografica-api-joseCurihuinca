package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    // POST /api/disco
    @PostMapping(
            value = "/disco",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> handlePostDiscoRequest(@RequestBody Disco disco) {

        boolean existeArtista = artistaRepository.existsById(disco.idArtista);
        if (!existeArtista) {
            return ResponseEntity
                    .status(400)
                    .body("No se puede crear el disco porque el artista no existe");
        }

        Disco creado = discoRepository.save(disco);
        return ResponseEntity.status(201).body(creado);
    }

    // GET /api/discos
    @GetMapping(
            value = "/discos",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return ResponseEntity.ok(discos);
    }

    // GET /api/disco/{id}
    @GetMapping(
            value = "/disco/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> handleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> disco = discoRepository.findById(id);
        if (disco.isPresent()) {
            return ResponseEntity.ok(disco.get());
        } else {
            return ResponseEntity.status(404).body("Disco no encontrado");
        }
    }

    // GET /api/artista/{id}/discos
    @GetMapping(
            value = "/artista/{id}/discos",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(idArtista);
        return ResponseEntity.ok(discos);
    }
}
