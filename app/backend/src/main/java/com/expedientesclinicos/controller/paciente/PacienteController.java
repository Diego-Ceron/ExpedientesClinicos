package com.expedientesclinicos.controller.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.common.PerfilSolicitante;
import com.expedientesclinicos.dto.paciente.PacienteCreateRequest;
import com.expedientesclinicos.dto.paciente.PacienteResponse;
import com.expedientesclinicos.dto.paciente.PacienteUpdateRequest;
import com.expedientesclinicos.service.paciente.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Operaciones para administrar pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @Operation(summary = "Crear paciente", description = "Registra un nuevo paciente en el sistema")
    public ResponseEntity<PacienteResponse> crear(@Valid @RequestBody PacienteCreateRequest request) {
        PacienteResponse respuesta = pacienteService.crearPaciente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{pacienteId}")
    @Operation(summary = "Actualizar paciente", description = "Modifica datos del paciente por ID")
    public PacienteResponse actualizar(@PathVariable Long pacienteId,
                                       @Valid @RequestBody PacienteUpdateRequest request) {
        return pacienteService.actualizarPaciente(pacienteId, request);
    }

    @DeleteMapping("/{pacienteId}")
    @Operation(summary = "Eliminar paciente", description = "Elimina el expediente de un paciente por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long pacienteId,
                                         @Valid @RequestBody ModificadorRequest solicitante) {
        pacienteService.eliminarPaciente(pacienteId, solicitante);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar pacientes", description = "Lista todos los pacientes disponibles para administradores")
    public List<PacienteResponse> listar(@RequestParam Long usuarioId,
                                         @RequestParam PerfilSolicitante perfil) {
        return pacienteService.listarTodos(ModificadorRequest.of(usuarioId, perfil));
    }

    @GetMapping("/sin-terapeuta")
    @Operation(summary = "Listar pacientes sin terapeuta", description = "Lista pacientes en espera sin terapeuta asignado")
    public List<PacienteResponse> listarSinTerapeuta(@RequestParam Long usuarioId,
                                                     @RequestParam PerfilSolicitante perfil) {
        return pacienteService.listarSinTerapeuta(ModificadorRequest.of(usuarioId, perfil));
    }

    @GetMapping("/{pacienteId}")
    @Operation(summary = "Obtener paciente", description = "Devuelve datos del paciente por ID")
    public PacienteResponse obtenerPorId(@PathVariable Long pacienteId,
                                          @RequestParam Long usuarioId,
                                          @RequestParam PerfilSolicitante perfil) {
        return pacienteService.obtenerPorId(pacienteId, ModificadorRequest.of(usuarioId, perfil));
    }

    @GetMapping("/terapeutas/{terapeutaId}")
    @Operation(summary = "Listar pacientes por terapeuta", description = "Lista todos los pacientes asignados a un terapeuta")
    public List<PacienteResponse> listarPorTerapeuta(@PathVariable Long terapeutaId,
                                                     @RequestParam Long usuarioId,
                                                     @RequestParam PerfilSolicitante perfil) {
        return pacienteService.listarPorTerapeuta(terapeutaId, ModificadorRequest.of(usuarioId, perfil));
    }
}
