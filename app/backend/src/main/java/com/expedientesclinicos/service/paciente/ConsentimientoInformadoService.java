package com.expedientesclinicos.service.paciente;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.paciente.ConsentimientoInformadoRequest;
import com.expedientesclinicos.dto.paciente.ConsentimientoInformadoResponse;
import com.expedientesclinicos.exception.AccessDeniedException;
import com.expedientesclinicos.exception.DomainException;
import com.expedientesclinicos.exception.ResourceNotFoundException;
import com.expedientesclinicos.model.paciente.ConsentimientoInformado;
import com.expedientesclinicos.model.paciente.Paciente;
import com.expedientesclinicos.model.paciente.Terapeuta;
import com.expedientesclinicos.repository.paciente.ConsentimientoInformadoRepository;
import com.expedientesclinicos.repository.paciente.PacienteRepository;

@Service
@Transactional
public class ConsentimientoInformadoService {

    private final PacienteRepository pacienteRepository;
    private final ConsentimientoInformadoRepository consentimientoInformadoRepository;

    public ConsentimientoInformadoService(PacienteRepository pacienteRepository,
                                          ConsentimientoInformadoRepository consentimientoInformadoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.consentimientoInformadoRepository = consentimientoInformadoRepository;
    }

    public ConsentimientoInformadoResponse crearOActualizar(Long pacienteId, ConsentimientoInformadoRequest request) {
        validarSolicitante(request.getSolicitante());
        Paciente paciente = buscarPaciente(pacienteId);
        validarAcceso(paciente, request.getSolicitante());

        ConsentimientoInformado consentimiento = consentimientoInformadoRepository.findByPacienteId(pacienteId)
                .orElseGet(() -> {
                    ConsentimientoInformado c = new ConsentimientoInformado();
                    c.setPaciente(paciente);
                    return c;
                });

        consentimiento.setAlcance(request.getAlcance());
        consentimiento.setRiesgos(request.getRiesgos());
        consentimiento.setAceptacion(request.getAceptacion());

        consentimientoInformadoRepository.save(consentimiento);
        return mapear(consentimiento);
    }

    @Transactional(readOnly = true)
    public ConsentimientoInformadoResponse obtener(Long pacienteId, ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        Paciente paciente = buscarPaciente(pacienteId);
        validarAcceso(paciente, solicitante);

        ConsentimientoInformado consentimiento = consentimientoInformadoRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Consentimiento no registrado para el paciente"));
        return mapear(consentimiento);
    }

    private ConsentimientoInformadoResponse mapear(ConsentimientoInformado c) {
        ConsentimientoInformadoResponse r = new ConsentimientoInformadoResponse();
        r.setId(c.getId());
        r.setAlcance(c.getAlcance());
        r.setRiesgos(c.getRiesgos());
        r.setAceptacion(c.getAceptacion());
        r.setFechaRegistro(c.getFechaRegistro());
        r.setUltimaActualizacion(c.getUltimaActualizacion());
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
            throw new AccessDeniedException("Solo el terapeuta asignado o un administrador puede gestionar el consentimiento");
        }
    }

    private void validarSolicitante(ModificadorRequest solicitante) {
        if (solicitante == null || solicitante.getUsuarioId() == null || solicitante.getPerfil() == null) {
            throw new DomainException("Debe indicar el solicitante que realiza la operación");
        }
    }
}
