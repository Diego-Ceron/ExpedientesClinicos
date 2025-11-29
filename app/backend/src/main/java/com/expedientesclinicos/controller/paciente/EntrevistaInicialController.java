package com.expedientesclinicos.controller.paciente;

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

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.common.PerfilSolicitante;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialRequest;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialResponse;
import com.expedientesclinicos.service.paciente.EntrevistaInicialService;
import com.expedientesclinicos.service.util.PdfService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes/{pacienteId}/entrevista")
public class EntrevistaInicialController {

    private final EntrevistaInicialService entrevistaInicialService;
    private final PdfService pdfService;

    public EntrevistaInicialController(EntrevistaInicialService entrevistaInicialService, PdfService pdfService) {
        this.entrevistaInicialService = entrevistaInicialService;
        this.pdfService = pdfService;
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

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdf(@PathVariable Long pacienteId,
                                      @RequestParam Long usuarioId,
                                      @RequestParam PerfilSolicitante perfil) {
        EntrevistaInicialResponse entrevista = entrevistaInicialService.obtener(pacienteId, ModificadorRequest.of(usuarioId, perfil));
        byte[] pdf = pdfService.generarPdfDesdeEntrevista(entrevista);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=entrevista_" + pacienteId + ".pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
