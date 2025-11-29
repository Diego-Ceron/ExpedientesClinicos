package com.expedientesclinicos.service.util;

import java.io.ByteArrayOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import com.expedientesclinicos.dto.paciente.ConsentimientoInformadoResponse;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialResponse;
import com.expedientesclinicos.dto.paciente.SesionClinicaResponse;

@Service
public class PdfService {

    public byte[] generarPdfDesdeSesion(SesionClinicaResponse sesion) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
                cs.newLineAtOffset(50, 750);
                cs.showText("Sesion clínica - ID: " + (sesion.getId() == null ? "-" : sesion.getId().toString()));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 720);
                cs.showText("Número sesión: " + sesion.getNumeroSesion());
                cs.newLineAtOffset(0, -18);
                cs.showText("Fecha: " + (sesion.getFecha() == null ? "" : sesion.getFecha().toString()));
                cs.newLineAtOffset(0, -18);
                cs.showText("Asistencia: " + (sesion.getAsistencia() == null ? "" : sesion.getAsistencia().name()));
                cs.newLineAtOffset(0, -18);
                cs.showText("Duración (min): " + (sesion.getDuracionMinutos() == null ? "" : sesion.getDuracionMinutos().toString()));
                cs.endText();
                // Bloques multilinea usando misma lógica que entrevista/consentimiento
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 630);
                cs.showText("Descripción:");
                cs.endText();
                escribirBloqueTexto(doc, sesion.getDescripcion(), 50, 610, 11);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 570);
                cs.showText("Observaciones:");
                cs.endText();
                escribirBloqueTexto(doc, sesion.getObservaciones(), 50, 550, 11);
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Error generando PDF", ex);
        }
    }

    public byte[] generarPdfDesdeEntrevista(EntrevistaInicialResponse entrevista) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
                cs.newLineAtOffset(50, 750);
                cs.showText("Entrevista Inicial - Paciente ID: " + (entrevista.getId() == null ? "-" : entrevista.getId()));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 720);
                cs.showText("Motivo de consulta:");
                cs.endText();

                escribirBloqueTexto(doc, entrevista.getMotivoConsulta(), 50, 700, 12);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 660);
                cs.showText("Antecedentes:");
                cs.endText();
                escribirBloqueTexto(doc, entrevista.getAntecedentes(), 50, 640, 11);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 600);
                cs.showText("Observaciones:");
                cs.endText();
                escribirBloqueTexto(doc, entrevista.getObservaciones(), 50, 580, 11);
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de entrevista", e);
        }
    }

    public byte[] generarPdfDesdeConsentimiento(ConsentimientoInformadoResponse consentimiento) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
                cs.newLineAtOffset(50, 750);
                cs.showText("Consentimiento Informado - Paciente ID: " + (consentimiento.getId() == null ? "-" : consentimiento.getId()));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 720);
                cs.showText("Alcance:");
                cs.endText();
                escribirBloqueTexto(doc, consentimiento.getAlcance(), 50, 700, 12);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 660);
                cs.showText("Riesgos y beneficios:");
                cs.endText();
                escribirBloqueTexto(doc, consentimiento.getRiesgos(), 50, 640, 11);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 600);
                cs.showText("Aceptación: " + (consentimiento.getAceptacion() == null ? "" : consentimiento.getAceptacion()));
                cs.endText();
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de consentimiento", e);
        }
    }

    private void escribirBloqueTexto(PDDocument doc, String texto, float x, float y, int fontSize) throws Exception {
        if (texto == null) texto = "";
        String trimmed = texto.trim();
        try (PDPageContentStream cs = new PDPageContentStream(doc, doc.getPage(doc.getNumberOfPages() - 1), PDPageContentStream.AppendMode.APPEND, true)) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, fontSize);
            cs.newLineAtOffset(x, y);
            // simple recorte para evitar overflow
            String content = trimmed.length() > 900 ? trimmed.substring(0, 900) : trimmed;
            cs.showText(content.replace('\n', ' '));
            cs.endText();
        }
    }
}
