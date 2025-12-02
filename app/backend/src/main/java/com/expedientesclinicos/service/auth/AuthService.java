package com.expedientesclinicos.service.auth;

import com.expedientesclinicos.dto.auth.LoginResponse;
import com.expedientesclinicos.repository.auth.EmailLoginCodeRepository;
import com.expedientesclinicos.repository.paciente.AdministrativoRepository;
import com.expedientesclinicos.repository.paciente.TerapeutaRepository;
import com.expedientesclinicos.model.auth.EmailLoginCode;
import com.expedientesclinicos.model.paciente.Administrativo;
import com.expedientesclinicos.model.paciente.Terapeuta;
import com.expedientesclinicos.service.util.EmailService;
import com.expedientesclinicos.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    private final TerapeutaRepository terapeutaRepository;
    private final AdministrativoRepository administrativoRepository;
    private final EmailLoginCodeRepository codeRepository;
    private final EmailService emailService;

    public AuthService(TerapeutaRepository terapeutaRepository,
                       AdministrativoRepository administrativoRepository,
                       EmailLoginCodeRepository codeRepository,
                       EmailService emailService) {
        this.terapeutaRepository = terapeutaRepository;
        this.administrativoRepository = administrativoRepository;
        this.codeRepository = codeRepository;
        this.emailService = emailService;
    }

    public void requestCode(String correo) {
        boolean existe = terapeutaRepository.findByCorreoIgnoreCase(correo).isPresent()
                || administrativoRepository.findByCorreoIgnoreCase(correo).isPresent();
        if (!existe) {
            throw new ResourceNotFoundException("Correo no registrado");
        }

        String code = generarCodigo();
        Instant ahora = Instant.now();
        EmailLoginCode entity = new EmailLoginCode();
        entity.setEmail(correo.toLowerCase());
        entity.setCode(code);
        entity.setCreatedAt(ahora);
        entity.setExpiresAt(ahora.plus(10, ChronoUnit.MINUTES));
        entity.setUsed(false);
        entity.setAttempts(0);
        codeRepository.save(entity);

        String subject = "Tu código de acceso";
        String body = "Tu código de verificación es: " + code + " (válido por 10 minutos).";
        emailService.send(correo, subject, body);
    }

    public LoginResponse verifyCode(String correo, String codigo) {
        Instant ahora = Instant.now();
        Optional<EmailLoginCode> opt = codeRepository.findByEmailAndCodeAndUsedFalseAndExpiresAtAfter(correo.toLowerCase(), codigo, ahora);
        EmailLoginCode record = opt.orElseThrow(() -> new ResourceNotFoundException("Código inválido o expirado"));

        record.setUsed(true);
        codeRepository.save(record);

        Optional<Terapeuta> ter = terapeutaRepository.findByCorreoIgnoreCase(correo);
        if (ter.isPresent()) {
            Terapeuta t = ter.get();
            return new LoginResponse(t.getId(), t.getNombre(), "TERAPEUTA");
        }
        Optional<Administrativo> adm = administrativoRepository.findByCorreoIgnoreCase(correo);
        if (adm.isPresent()) {
            Administrativo a = adm.get();
            return new LoginResponse(a.getId(), a.getNombre(), "ADMINISTRADOR");
        }
        throw new ResourceNotFoundException("Usuario no encontrado para el correo indicado");
    }

    private String generarCodigo() {
        Random r = new Random();
        int n = 100000 + r.nextInt(900000);
        return String.valueOf(n);
    }
}
