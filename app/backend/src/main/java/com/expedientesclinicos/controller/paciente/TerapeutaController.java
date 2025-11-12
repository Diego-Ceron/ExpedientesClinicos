package com.expedientesclinicos.controller.paciente;

import com.expedientesclinicos.dto.paciente.TerapeutaCreateRequest;
import com.expedientesclinicos.dto.paciente.TerapeutaSummary;
import com.expedientesclinicos.service.paciente.TerapeutaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/terapeutas")
public class TerapeutaController {

    private final TerapeutaService terapeutaService;

    public TerapeutaController(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @PostMapping
    public ResponseEntity<TerapeutaSummary> crear(@Valid @RequestBody TerapeutaCreateRequest request) {
        TerapeutaSummary created = terapeutaService.crearTerapeuta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<TerapeutaSummary> listar() {
        return terapeutaService.listarTodos();
    }

    @GetMapping("/{id}")
    public TerapeutaSummary obtener(@PathVariable Long id) {
        return terapeutaService.obtenerPorId(id);
    }
}
