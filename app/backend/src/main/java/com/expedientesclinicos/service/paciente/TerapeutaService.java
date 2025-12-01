package com.expedientesclinicos.service.paciente;

import com.expedientesclinicos.dto.paciente.TerapeutaCreateRequest;
import com.expedientesclinicos.dto.paciente.TerapeutaDetailResponse;
import com.expedientesclinicos.dto.paciente.TerapeutaSummary;
import com.expedientesclinicos.dto.paciente.TerapeutaUpdateRequest;
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
        t.setNombre(request.getNombre().trim());
        t.setCorreo(request.getCorreo().trim());
        t.setRol("TERAPEUTA");
        t.setEspecialidad(request.getEspecialidad().trim());
        t.setCedulaProfesional(request.getCedulaProfesional().trim());
        t.setTelefono(normalizeOptional(request.getTelefono()));

        Terapeuta saved = terapeutaRepository.save(t);
        return mapToSummary(saved);
    }

    public List<TerapeutaSummary> listarTodos() {
        return terapeutaRepository.findAll()
                .stream()
                .map(this::mapToSummary)
                .collect(Collectors.toList());
    }

    public TerapeutaDetailResponse obtenerDetalle(Long id) {
        Terapeuta terapeuta = findByIdOrThrow(id);
        return mapToDetail(terapeuta);
    }

    public TerapeutaDetailResponse actualizarTerapeuta(Long id, TerapeutaUpdateRequest request) {
        Terapeuta terapeuta = findByIdOrThrow(id);
        terapeuta.setNombre(request.getNombre().trim());
        terapeuta.setCorreo(request.getCorreo().trim());
        terapeuta.setEspecialidad(request.getEspecialidad().trim());
        terapeuta.setCedulaProfesional(request.getCedulaProfesional().trim());
        terapeuta.setTelefono(normalizeOptional(request.getTelefono()));

        Terapeuta updated = terapeutaRepository.save(terapeuta);
        return mapToDetail(updated);
    }

    public void eliminarTerapeuta(Long id) {
        Terapeuta terapeuta = findByIdOrThrow(id);
        terapeutaRepository.delete(terapeuta);
    }

    private TerapeutaSummary mapToSummary(Terapeuta terapeuta) {
        return new TerapeutaSummary(terapeuta.getId(), terapeuta.getNombre(), terapeuta.getEspecialidad());
    }

    private TerapeutaDetailResponse mapToDetail(Terapeuta terapeuta) {
        return new TerapeutaDetailResponse(
                terapeuta.getId(),
                terapeuta.getNombre(),
                terapeuta.getCorreo(),
                terapeuta.getEspecialidad(),
                terapeuta.getCedulaProfesional(),
                terapeuta.getTelefono()
        );
    }

    private Terapeuta findByIdOrThrow(Long id) {
        return terapeutaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terapeuta no encontrado"));
    }

    private String normalizeOptional(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
