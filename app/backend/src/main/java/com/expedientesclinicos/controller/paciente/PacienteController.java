package com.expedientesclinicos.controller.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.common.PerfilSolicitante;
import com.expedientesclinicos.dto.paciente.PacienteCreateRequest;
import com.expedientesclinicos.dto.paciente.PacienteResponse;
import com.expedientesclinicos.dto.paciente.PacienteUpdateRequest;
import com.expedientesclinicos.service.paciente.PacienteService;
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
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> crear(@Valid @RequestBody PacienteCreateRequest request) {
        PacienteResponse respuesta = pacienteService.crearPaciente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{pacienteId}")
    public PacienteResponse actualizar(@PathVariable Long pacienteId,
                                       @Valid @RequestBody PacienteUpdateRequest request) {
        return pacienteService.actualizarPaciente(pacienteId, request);
    }

    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long pacienteId,
                                         @Valid @RequestBody ModificadorRequest solicitante) {
        pacienteService.eliminarPaciente(pacienteId, solicitante);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pacienteId}")
    public PacienteResponse obtenerPorId(@PathVariable Long pacienteId,
                                          @RequestParam Long usuarioId,
                                          @RequestParam PerfilSolicitante perfil) {
        return pacienteService.obtenerPorId(pacienteId, ModificadorRequest.of(usuarioId, perfil));
    }

    @GetMapping("/terapeutas/{terapeutaId}")
    public List<PacienteResponse> listarPorTerapeuta(@PathVariable Long terapeutaId,
                                                     @RequestParam Long usuarioId,
                                                     @RequestParam PerfilSolicitante perfil) {
        return pacienteService.listarPorTerapeuta(terapeutaId, ModificadorRequest.of(usuarioId, perfil));
    }
}
