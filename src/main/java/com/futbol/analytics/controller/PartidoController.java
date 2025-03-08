package com.futbol.analytics.controller;

import com.futbol.analytics.dto.PartidoDTO;
import com.futbol.analytics.entity.Partido;
import com.futbol.analytics.service.PartidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/partidos")
public class PartidoController {
    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public List<Partido> obtenerPartidos() {
        return partidoService.obtenerPartidos();
    }

    @PostMapping
    public ResponseEntity<?> guardarPartido(@RequestBody PartidoDTO partidoDTO) {
        if (partidoDTO.getEquipoLocal() == null || partidoDTO.getEquipoVisitante() == null || partidoDTO.getFecha() == null) {
            throw new IllegalArgumentException("Los campos obligatorios no pueden ser nulos");
        }

        if (partidoDTO.getGolesLocal() < 0 || partidoDTO.getGolesVisitante() < 0) {
            throw new IllegalArgumentException("Los goles no pueden ser negativos");
        }

        return ResponseEntity.ok(partidoService.guardarPartido(partidoDTO));
    }


    @DeleteMapping("/{id}")
    public void eliminarPartido(@PathVariable Long id) {
        partidoService.eliminarPartido(id);
    }

    // Manejo de errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }
}

