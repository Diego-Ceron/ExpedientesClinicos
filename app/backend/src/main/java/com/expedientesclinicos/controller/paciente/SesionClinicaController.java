package com.expedientesclinicos.controller.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.common.PerfilSolicitante;
import com.expedientesclinicos.dto.paciente.SesionClinicaCreateRequest;
import com.expedientesclinicos.dto.paciente.SesionClinicaResponse;
import com.expedientesclinicos.dto.paciente.SesionClinicaUpdateRequest;
import com.expedientesclinicos.dto.paciente.SesionRevertRequest;
import com.expedientesclinicos.model.paciente.SesionClinicaHistory;
import com.expedientesclinicos.service.paciente.SesionClinicaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/pacientes/{pacienteId}/sesiones")
public class SesionClinicaController {

    private final SesionClinicaService sesionClinicaService;

    public SesionClinicaController(SesionClinicaService sesionClinicaService) {
        this.sesionClinicaService = sesionClinicaService;
    }

    @PostMapping
    public ResponseEntity<SesionClinicaResponse> registrar(@PathVariable Long pacienteId,
                                                           @Valid @RequestBody SesionClinicaCreateRequest request) {
        SesionClinicaResponse response = sesionClinicaService.registrarSesion(pacienteId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{sesionId}")
    public SesionClinicaResponse actualizar(@PathVariable Long pacienteId,
                                            @PathVariable Long sesionId,
                                            @Valid @RequestBody SesionClinicaUpdateRequest request) {
        return sesionClinicaService.actualizarSesion(pacienteId, sesionId, request);
    }

    @GetMapping("/{sesionId}/history")
    public List<SesionClinicaHistory> historial(@PathVariable Long pacienteId,
                                                @PathVariable Long sesionId,
                                                @RequestParam Long usuarioId,
                                                @RequestParam PerfilSolicitante perfil) {
        return sesionClinicaService.listarHistorial(pacienteId, sesionId, ModificadorRequest.of(usuarioId, perfil));
    }

    @PostMapping("/{sesionId}/revert")
    public SesionClinicaResponse revertir(@PathVariable Long pacienteId,
                                         @PathVariable Long sesionId,
                                         @Valid @RequestBody SesionRevertRequest request) {
        return sesionClinicaService.revertirSesion(pacienteId, sesionId, request.getHistoryId(), request.getSolicitante());
    }

    @GetMapping("/{sesionId}/pdf")
    public ResponseEntity<byte[]> pdf(@PathVariable Long pacienteId,
                                      @PathVariable Long sesionId,
                                      @RequestParam Long usuarioId,
                                      @RequestParam PerfilSolicitante perfil) {
        byte[] pdf = sesionClinicaService.generarPdfSesion(pacienteId, sesionId, ModificadorRequest.of(usuarioId, perfil));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=sesion_" + sesionId + ".pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping
    public List<SesionClinicaResponse> listar(@PathVariable Long pacienteId,
                                              @RequestParam Long usuarioId,
                                              @RequestParam PerfilSolicitante perfil) {
        return sesionClinicaService.listarSesiones(pacienteId, ModificadorRequest.of(usuarioId, perfil));
    }
}
