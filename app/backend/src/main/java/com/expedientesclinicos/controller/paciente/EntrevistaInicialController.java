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
import com.expedientesclinicos.dto.paciente.EntrevistaInicialRequest;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialResponse;
import com.expedientesclinicos.service.paciente.EntrevistaInicialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes/{pacienteId}/entrevista")
public class EntrevistaInicialController {

    private final EntrevistaInicialService entrevistaInicialService;

    public EntrevistaInicialController(EntrevistaInicialService entrevistaInicialService) {
        this.entrevistaInicialService = entrevistaInicialService;
    }

    @GetMapping
    public EntrevistaInicialResponse obtener(@PathVariable Long pacienteId,
                                             @RequestParam Long usuarioId,
                                             @RequestParam PerfilSolicitante perfil) {
        return entrevistaInicialService.obtener(pacienteId, ModificadorRequest.of(usuarioId, perfil));
    }

    @PostMapping
    public ResponseEntity<EntrevistaInicialResponse> crear(@PathVariable Long pacienteId,
                                                           @Valid @RequestBody EntrevistaInicialRequest request) {
        EntrevistaInicialResponse r = entrevistaInicialService.crearOActualizar(pacienteId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping
    public EntrevistaInicialResponse actualizar(@PathVariable Long pacienteId,
                                                @Valid @RequestBody EntrevistaInicialRequest request) {
        return entrevistaInicialService.crearOActualizar(pacienteId, request);
    }
}
