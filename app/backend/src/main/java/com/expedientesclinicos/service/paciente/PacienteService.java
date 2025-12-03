package com.expedientesclinicos.service.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.dto.paciente.PacienteCreateRequest;
import com.expedientesclinicos.dto.paciente.PacienteResponse;
import com.expedientesclinicos.dto.paciente.PacienteUpdateRequest;
import com.expedientesclinicos.dto.paciente.TerapeutaSummary;
import com.expedientesclinicos.exception.AccessDeniedException;
import com.expedientesclinicos.exception.DomainException;
import com.expedientesclinicos.exception.ResourceNotFoundException;
import com.expedientesclinicos.model.paciente.EstadoExpediente;
import com.expedientesclinicos.model.paciente.Paciente;
import com.expedientesclinicos.model.paciente.Terapeuta;
import com.expedientesclinicos.repository.paciente.PacienteRepository;
import com.expedientesclinicos.repository.paciente.TerapeutaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final TerapeutaRepository terapeutaRepository;

    public PacienteService(PacienteRepository pacienteRepository, TerapeutaRepository terapeutaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.terapeutaRepository = terapeutaRepository;
    }

    public PacienteResponse crearPaciente(PacienteCreateRequest request) {
        validarSolicitante(request.getSolicitante());
        Terapeuta terapeuta = obtenerTerapeutaOpcional(request);

        Paciente paciente = new Paciente();
        mapearDatosPaciente(request, paciente, terapeuta);

        pacienteRepository.save(paciente);
        return mapearRespuesta(paciente);
    }

    public PacienteResponse actualizarPaciente(Long pacienteId, PacienteUpdateRequest request) {
        validarSolicitante(request.getSolicitante());
        Paciente paciente = buscarPaciente(pacienteId);

        validarAccesoPaciente(paciente, request.getSolicitante());

        Terapeuta terapeuta = obtenerTerapeutaOpcional(request);

        mapearDatosPaciente(request, paciente, terapeuta);
        return mapearRespuesta(paciente);
    }

    public void eliminarPaciente(Long pacienteId, ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        Paciente paciente = buscarPaciente(pacienteId);
        validarAccesoPaciente(paciente, solicitante);
        pacienteRepository.delete(paciente);
    }

    @Transactional(readOnly = true)
    public PacienteResponse obtenerPorId(Long pacienteId, ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        Paciente paciente = buscarPaciente(pacienteId);
        validarAccesoPaciente(paciente, solicitante);
        return mapearRespuesta(paciente);
    }

    @Transactional(readOnly = true)
    public List<PacienteResponse> listarPorTerapeuta(Long terapeutaId, ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        if (!solicitante.esAdministrador() && !Objects.equals(solicitante.getUsuarioId(), terapeutaId)) {
            throw new AccessDeniedException("Solo el terapeuta asignado o un administrador puede consultar la lista");
        }
        return pacienteRepository.findByTerapeutaAsignadoId(terapeutaId)
                .stream()
                .map(this::mapearRespuesta)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PacienteResponse> listarTodos(ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        if (!solicitante.esAdministrador()) {
            throw new AccessDeniedException("Solo un administrador puede consultar todos los pacientes");
        }
        return pacienteRepository.findAll()
                .stream()
                .map(this::mapearRespuesta)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PacienteResponse> listarSinTerapeuta(ModificadorRequest solicitante) {
        validarSolicitante(solicitante);
        if (!solicitante.esAdministrador()) {
            throw new AccessDeniedException("Solo un administrador puede consultar pacientes sin terapeuta");
        }
        return pacienteRepository.findByTerapeutaAsignadoIsNull()
                .stream()
                .map(this::mapearRespuesta)
                .collect(Collectors.toList());
    }

    private void mapearDatosPaciente(PacienteCreateRequest request, Paciente paciente, Terapeuta terapeuta) {
        paciente.setNombreCompleto(request.getNombreCompleto());
        paciente.setCelular(request.getCelular());
        paciente.setEmail(request.getEmail());
        paciente.setTipoServicio(request.getTipoServicio());
        paciente.setEstado(request.getEstado());
        paciente.setSexo(request.getSexo());
        paciente.setEdad(request.getEdad());
        paciente.setFechaRegistro(request.getFechaRegistro());
        paciente.setFechaProximaSesion(request.getFechaProximaSesion());
        paciente.setTerapeutaAsignado(terapeuta);
    }

    private Terapeuta obtenerTerapeutaOpcional(PacienteCreateRequest request) {
        Long terapeutaId = request.getTerapeutaId();
        if (terapeutaId == null) {
            validarPacienteEnEsperaSinTerapeuta(request);
            return null;
        }

        Terapeuta terapeuta = buscarTerapeuta(terapeutaId);
        if (!request.getSolicitante().esAdministrador()
                && !Objects.equals(request.getSolicitante().getUsuarioId(), terapeuta.getId())) {
            throw new AccessDeniedException("Un terapeuta solo puede registrar o reasignar pacientes propios");
        }
        return terapeuta;
    }

    private Paciente buscarPaciente(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
    }

    private Terapeuta buscarTerapeuta(Long terapeutaId) {
        return terapeutaRepository.findById(terapeutaId)
                .orElseThrow(() -> new ResourceNotFoundException("Terapeuta no encontrado"));
    }

    private void validarAccesoPaciente(Paciente paciente, ModificadorRequest solicitante) {
        if (solicitante.esAdministrador()) {
            return;
        }
        Terapeuta terapeuta = paciente.getTerapeutaAsignado();
        if (terapeuta == null || !Objects.equals(terapeuta.getId(), solicitante.getUsuarioId())) {
            throw new AccessDeniedException("Solo el terapeuta asignado o un administrador puede modificar el expediente");
        }
    }

    private void validarSolicitante(ModificadorRequest solicitante) {
        if (solicitante == null || solicitante.getUsuarioId() == null || solicitante.getPerfil() == null) {
            throw new DomainException("Debe indicar el solicitante que realiza la operación");
        }
    }

    private void validarPacienteEnEsperaSinTerapeuta(PacienteCreateRequest request) {
        if (!request.getSolicitante().esAdministrador()) {
            throw new AccessDeniedException("Solo un administrador puede crear o dejar a un paciente en espera sin terapeuta asignado");
        }
        if (request.getEstado() != EstadoExpediente.EN_ESPERA) {
            throw new DomainException("Un paciente sin terapeuta debe permanecer en estado EN_ESPERA");
        }
    }

    private PacienteResponse mapearRespuesta(Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setNombreCompleto(paciente.getNombreCompleto());
        response.setCelular(paciente.getCelular());
        response.setEmail(paciente.getEmail());
        response.setTipoServicio(paciente.getTipoServicio());
        response.setEstado(paciente.getEstado());
        response.setSexo(paciente.getSexo());
        response.setEdad(paciente.getEdad());
        response.setFechaRegistro(paciente.getFechaRegistro());
        response.setFechaProximaSesion(paciente.getFechaProximaSesion());

        Terapeuta terapeuta = paciente.getTerapeutaAsignado();
        if (terapeuta != null) {
            response.setTerapeutaAsignado(new TerapeutaSummary(
                    terapeuta.getId(),
                    terapeuta.getNombre(),
                    terapeuta.getEspecialidad()
            ));
        }
        return response;
    }
}
