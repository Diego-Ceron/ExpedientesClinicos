package com.expedientesclinicos.controller;

import com.expedientesclinicos.dto.auth.LoginResponse;
import com.expedientesclinicos.dto.auth.RequestLoginCodeRequest;
import com.expedientesclinicos.dto.auth.VerifyLoginCodeRequest;
import com.expedientesclinicos.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Login por correo con código de verificación")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/request-code")
    @Operation(summary = "Solicitar código", description = "Envía un código de verificación al correo registrado si existe")
    public ResponseEntity<Void> requestCode(@Valid @RequestBody RequestLoginCodeRequest request) {
        authService.requestCode(request.getCorreo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-code")
    @Operation(summary = "Verificar código", description = "Verifica el código y devuelve los datos del usuario/rol")
    public LoginResponse verifyCode(@Valid @RequestBody VerifyLoginCodeRequest request) {
        return authService.verifyCode(request.getCorreo(), request.getCodigo());
    }
}
