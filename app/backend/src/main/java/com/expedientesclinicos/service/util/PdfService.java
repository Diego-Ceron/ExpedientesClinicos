package com.expedientesclinicos.service.util;

import com.expedientesclinicos.dto.paciente.SesionClinicaResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

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

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_OBLIQUE, 11);
                cs.newLineAtOffset(50, 630);
                cs.showText("Descripción:");
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(50, 610);
                String desc = sesion.getDescripcion() == null ? "" : sesion.getDescripcion();
                cs.showText(desc.length() > 800 ? desc.substring(0, 800) : desc);
                cs.endText();
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Error generando PDF", ex);
        }
    }
}
