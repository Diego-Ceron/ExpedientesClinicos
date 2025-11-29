package com.expedientesclinicos.controller.paciente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.common.PerfilSolicitante;
import com.expedientesclinicos.dto.paciente.ConsentimientoInformadoRequest;
import com.expedientesclinicos.dto.paciente.ConsentimientoInformadoResponse;
import com.expedientesclinicos.service.paciente.ConsentimientoInformadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes/{pacienteId}/consentimiento")
public class ConsentimientoInformadoController {

    private final ConsentimientoInformadoService consentimientoInformadoService;

    public ConsentimientoInformadoController(ConsentimientoInformadoService consentimientoInformadoService) {
        this.consentimientoInformadoService = consentimientoInformadoService;
    }

    @GetMapping
    public ConsentimientoInformadoResponse obtener(@PathVariable Long pacienteId,
                                                   @RequestParam Long usuarioId,
                                                   @RequestParam PerfilSolicitante perfil) {
        return consentimientoInformadoService.obtener(pacienteId, ModificadorRequest.of(usuarioId, perfil));
    }

    @PostMapping
    public ResponseEntity<ConsentimientoInformadoResponse> crear(@PathVariable Long pacienteId,
                                                                 @Valid @RequestBody ConsentimientoInformadoRequest request) {
        ConsentimientoInformadoResponse r = consentimientoInformadoService.crearOActualizar(pacienteId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping
    public ConsentimientoInformadoResponse actualizar(@PathVariable Long pacienteId,
                                                      @Valid @RequestBody ConsentimientoInformadoRequest request) {
        return consentimientoInformadoService.crearOActualizar(pacienteId, request);
    }
}
