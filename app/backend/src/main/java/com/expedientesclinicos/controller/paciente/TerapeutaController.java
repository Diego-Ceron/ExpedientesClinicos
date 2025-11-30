package com.expedientesclinicos.controller.paciente;

import com.expedientesclinicos.dto.paciente.TerapeutaCreateRequest;
import com.expedientesclinicos.dto.paciente.TerapeutaDetailResponse;
import com.expedientesclinicos.dto.paciente.TerapeutaSummary;
import com.expedientesclinicos.dto.paciente.TerapeutaUpdateRequest;
import com.expedientesclinicos.service.paciente.TerapeutaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/terapeutas")
@Tag(name = "Terapeutas", description = "Gestión de terapeutas y sus datos")
public class TerapeutaController {

    private final TerapeutaService terapeutaService;

    public TerapeutaController(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @PostMapping
    @Operation(summary = "Crear terapeuta", description = "Registra un nuevo terapeuta en el sistema")
    public ResponseEntity<TerapeutaSummary> crear(@Valid @RequestBody TerapeutaCreateRequest request) {
        TerapeutaSummary created = terapeutaService.crearTerapeuta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Listar terapeutas", description = "Obtiene listado resumido de terapeutas")
    public List<TerapeutaSummary> listar() {
        return terapeutaService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle terapeuta", description = "Recupera información detallada de un terapeuta por su ID")
    public TerapeutaDetailResponse obtener(@PathVariable Long id) {
        return terapeutaService.obtenerDetalle(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar terapeuta", description = "Modifica los datos de un terapeuta")
    public TerapeutaDetailResponse actualizar(@PathVariable Long id,
                                              @Valid @RequestBody TerapeutaUpdateRequest request) {
        return terapeutaService.actualizarTerapeuta(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar terapeuta", description = "Elimina un terapeuta del sistema")
    public void eliminar(@PathVariable Long id) {
        terapeutaService.eliminarTerapeuta(id);
    }
}
