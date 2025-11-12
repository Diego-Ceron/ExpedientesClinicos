package com.expedientesclinicos.service.paciente;

import com.expedientesclinicos.dto.paciente.TerapeutaCreateRequest;
import com.expedientesclinicos.dto.paciente.TerapeutaSummary;
import com.expedientesclinicos.exception.ResourceNotFoundException;
import com.expedientesclinicos.model.paciente.Terapeuta;
import com.expedientesclinicos.repository.paciente.TerapeutaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerapeutaService {

    private final TerapeutaRepository terapeutaRepository;

    public TerapeutaService(TerapeutaRepository terapeutaRepository) {
        this.terapeutaRepository = terapeutaRepository;
    }

    public TerapeutaSummary crearTerapeuta(TerapeutaCreateRequest request) {
        Terapeuta t = new Terapeuta();
        t.setNombre(request.getNombre());
        t.setCorreo(request.getCorreo());
        t.setPassword(request.getPassword());
        t.setRol("TERAPEUTA");
        t.setEspecialidad(request.getEspecialidad());
        t.setCedulaProfesional(request.getCedulaProfesional());
        t.setTelefono(request.getTelefono());

        Terapeuta saved = terapeutaRepository.save(t);
        return new TerapeutaSummary(saved.getId(), saved.getNombre(), saved.getEspecialidad());
    }

    public List<TerapeutaSummary> listarTodos() {
        return terapeutaRepository.findAll()
                .stream()
                .map(t -> new TerapeutaSummary(t.getId(), t.getNombre(), t.getEspecialidad()))
                .collect(Collectors.toList());
    }

    public TerapeutaSummary obtenerPorId(Long id) {
        Terapeuta t = terapeutaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terapeuta no encontrado"));
        return new TerapeutaSummary(t.getId(), t.getNombre(), t.getEspecialidad());
    }
}
