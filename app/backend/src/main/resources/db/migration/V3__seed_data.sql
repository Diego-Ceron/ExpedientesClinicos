-- V3__seed_data.sql
-- Seed data para desarrollo: terapeutas, administrativos, pacientes, sesiones y historial

-- Terapeutas:
INSERT INTO terapeutas (nombre, correo, password, rol, especialidad, cedula_profesional, telefono) VALUES
  ('Ana Perez', 'ana.perez@example.com', 'anaperez', 'TERAPEUTA', 'Psicología clínica', 'CED-1001', '555-1001'),
  ('Luis Martinez', 'luis.martinez@example.com', 'luismartinez', 'TERAPEUTA', 'Terapia familiar', 'CED-1002', '555-1002');

-- Administrativos:
INSERT INTO administrativos (nombre, correo, password, rol, departamento, puesto) VALUES
  ('Sofia Lopez', 'sofia.lopez@example.com', 'sofialopez', 'ADMIN', 'Recepción', 'Recepcion'),
  ('Carlos Diaz', 'carlos.diaz@example.com', 'carlosdiaz', 'ADMIN', 'Administración', 'Administración');

INSERT INTO pacientes (nombre_completo, celular, email, tipo_servicio, estado, sexo, edad, fecha_registro, fecha_proxima_sesion, terapeuta_id) VALUES
  ('Juan Gomez', '555-2001', 'juan.gomez@example.com', 'TERAPIA_INDIVIDUAL', 'ACTIVO', 'MASCULINO', 30, '2025-11-01', NULL, 1),
  ('Mariana Sanchez', '555-2002', 'mariana.sanchez@example.com', 'TERAPIA_FAMILIAR', 'ACTIVO', 'FEMENINO', 28, '2025-11-02', NULL, 1),
  ('Pedro Ramirez', '555-2003', 'pedro.ramirez@example.com', 'TERAPIA_GRUPAL', 'ACTIVO', 'MASCULINO', 45, '2025-11-03', NULL, 2);

-- Sesiones clínicas:
INSERT INTO sesiones_clinicas (paciente_id, terapeuta_id, numero_sesion, tipo_sesion, fecha, asistencia, duracion_minutos, motivo_cancelacion, descripcion, observaciones) VALUES
  (1, 1, 1, 'EVALUACION_INICIAL', '2025-11-12', 'ASISTIO', 60, NULL, 'Primera sesión de evaluación', NULL),
  (2, 1, 1, 'INDIVIDUAL', '2025-11-13', 'CANCELADA', 0, 'Paciente canceló por enfermedad', 'Primera sesión', NULL),
  (3, 2, 2, 'SEGUIMIENTO', '2025-11-14', 'ASISTIO', 45, NULL, 'Seguimiento', NULL);

-- Historial de sesiones
INSERT INTO sesiones_clinicas_history (sesion_id, paciente_id, snapshot, modificado_por, comentario) VALUES
  (1, 1, '{"descripcion":"Primera sesión de evaluación","duracion_minutos":60}', 1, 'Creación inicial');

/*
endpoint terapeutas: http://localhost:8080/api/terapeutas
endpoint pdf : http://localhost:8080/api/pacientes/1/sesiones/1/pdf?usuarioId=1&perfil=TERAPEUTA
endpoint pacientes: http://localhost:8080/api/pacientes/terapeutas/1?usuarioId=1&perfil=TERAPEUTA
endpoint sesiones: http://localhost:8080/api/pacientes/1/sesiones?usuarioId=1&perfil=TERAPEUTA
*/
