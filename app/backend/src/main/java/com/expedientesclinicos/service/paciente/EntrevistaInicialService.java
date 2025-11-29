package com.expedientesclinicos.service.paciente;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialRequest;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialResponse;
import com.expedientesclinicos.exception.AccessDeniedException;
import com.expedientesclinicos.exception.DomainException;
import com.expedientesclinicos.exception.ResourceNotFoundException;
import com.expedientesclinicos.model.paciente.EntrevistaInicial;
import com.expedientesclinicos.model.paciente.Paciente;
import com.expedientesclinicos.model.paciente.Terapeuta;
import com.expedientesclinicos.repository.paciente.EntrevistaInicialRepository;
import com.expedientesclinicos.repository.paciente.PacienteRepository;

@Service
@Transactional
public class EntrevistaInicialService {

    private final PacienteRepository pacienteRepository;
    private final EntrevistaInicialRepository entrevistaInicialRepository;

    public EntrevistaInicialService(PacienteRepository pacienteRepository,
                                    EntrevistaInicialRepository entrevistaInicialRepository) {
        this.pacienteRepository = pacienteRepository;
        this.entrevistaInicialRepository = entrevistaInicialRepository;
    }

    public EntrevistaInicialResponse crearOActualizar(Long pacienteId, EntrevistaInicialRequest request) {
        validarSolicitante(request.getSolicitante());
        Paciente paciente = buscarPaciente(pacienteId);
        validarAcceso(paciente, request.getSolicitante());

        EntrevistaInicial entrevista = entrevistaInicialRepository.findByPacienteId(pacienteId)
                .orElseGet(() -> {
                    EntrevistaInicial e = new EntrevistaInicial();
                    e.setPaciente(paciente);
                    return e;
                });

        entrevista.setMotivoConsulta(request.getMotivoConsulta());
        entrevista.setAntecedentes(request.getAntecedentes());
        entrevista.setObservaciones(request.getObservaciones());

        entrevistaInicialRepository.save(entrevista);
        return mapear(entrevista);
    }

    @Transactional(readOnly = true)
    public EntrevistaInicialResponse obtener(Long pacienteId, ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        Paciente paciente = buscarPaciente(pacienteId);
        validarAcceso(paciente, solicitante);

        EntrevistaInicial entrevista = entrevistaInicialRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrevista no registrada para el paciente"));
        return mapear(entrevista);
    }

    private EntrevistaInicialResponse mapear(EntrevistaInicial e) {
        EntrevistaInicialResponse r = new EntrevistaInicialResponse();
        r.setId(e.getId());
        r.setMotivoConsulta(e.getMotivoConsulta());
        r.setAntecedentes(e.getAntecedentes());
        r.setObservaciones(e.getObservaciones());
        r.setFechaRegistro(e.getFechaRegistro());
        r.setUltimaActualizacion(e.getUltimaActualizacion());
        return r;
    }

    private Paciente buscarPaciente(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
    }

    private void validarAcceso(Paciente paciente, ModificadorRequest solicitante) {
        if (solicitante.esAdministrador()) return;
        Terapeuta terapeuta = paciente.getTerapeutaAsignado();
        if (terapeuta == null || !Objects.equals(terapeuta.getId(), solicitante.getUsuarioId())) {
            throw new AccessDeniedException("Solo el terapeuta asignado o un administrador puede gestionar la entrevista");
        }
    }

    private void validarSolicitante(ModificadorRequest solicitante) {
        if (solicitante == null || solicitante.getUsuarioId() == null || solicitante.getPerfil() == null) {
            throw new DomainException("Debe indicar el solicitante que realiza la operación");
        }
    }
}
