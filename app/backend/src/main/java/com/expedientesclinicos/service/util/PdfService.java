package com.expedientesclinicos.service.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import com.expedientesclinicos.dto.paciente.ConsentimientoInformadoResponse;
import com.expedientesclinicos.dto.paciente.EntrevistaInicialResponse;
import com.expedientesclinicos.dto.paciente.SesionClinicaResponse;

@Service
public class PdfService {

    private static final float MARGIN = 50f;
    private static final float LEADING_FACTOR = 1.35f;

    private static class PageCtx {
        PDDocument doc;
        PDPage page;
        PDPageContentStream cs;
        float y;
        float contentWidth;
        String headerTitle;
    }

    private PageCtx startPage(PDDocument doc, String titulo) throws Exception {
        PageCtx ctx = new PageCtx();
        ctx.doc = doc;
        ctx.page = new PDPage(PDRectangle.LETTER);
        doc.addPage(ctx.page);
        ctx.contentWidth = ctx.page.getMediaBox().getWidth() - (2 * MARGIN);
        ctx.cs = new PDPageContentStream(doc, ctx.page);
        ctx.headerTitle = titulo;

        float pageTop = ctx.page.getMediaBox().getHeight() - MARGIN;
        float titleX = MARGIN;

        PDImageXObject logo = tryLoadLogo(doc);
        if (logo != null) {
            // Dimensiones logo
            float maxW = 250f;
            float maxH = 60f;
            float scale = Math.min(maxW / logo.getWidth(), maxH / logo.getHeight());
            float drawW = logo.getWidth() * scale;
            float drawH = logo.getHeight() * scale;
            float rightX = MARGIN + ctx.contentWidth - drawW;
            // Colocar la base del logo ligeramente por encima de la línea separadora (que está en pageTop - 24)
            float logoY = pageTop - 20f;
            ctx.cs.drawImage(logo, rightX, logoY, drawW, drawH);
            titleX = MARGIN;
        }

        ctx.cs.beginText();
        ctx.cs.setNonStrokingColor(Color.BLACK);
        ctx.cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
        ctx.cs.newLineAtOffset(titleX, pageTop);
        ctx.cs.showText(titulo);
        ctx.cs.endText();

        // línea separadora
            // Línea separadora bajo el encabezado
            // ALTURA DE LA LÍNEA SEPARADORA: se dibuja a la coordenada Y (pageTop - 12f).
            // Ajusta este valor para subir/bajar la línea respecto al encabezado.
            ctx.cs.setStrokingColor(new Color(0, 0, 0));
            ctx.cs.setLineWidth(1.5f);
            ctx.cs.moveTo(MARGIN, pageTop - 24f);
            ctx.cs.lineTo(MARGIN + ctx.contentWidth, pageTop - 24f);
            ctx.cs.stroke();

        ctx.y = ctx.page.getMediaBox().getHeight() - MARGIN - 40;
        return ctx;
    }

    private void endPage(PageCtx ctx) throws Exception {
        if (ctx != null && ctx.cs != null) {
            ctx.cs.close();
        }
    }

    private PDImageXObject tryLoadLogo(PDDocument doc) {
        String[] candidates = new String[] {
            "/logo.png",
            "/static/logo.png",
            "/static/img/logo.png",
            "/public/logo.png"
        };
        for (String path : candidates) {
            try (InputStream is = getClass().getResourceAsStream(path)) {
                if (is == null) continue;
                byte[] bytes = is.readAllBytes();
                return PDImageXObject.createFromByteArray(doc, bytes, "logo");
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private void ensureSpace(PageCtx ctx, float needed) throws Exception {
        float bottom = MARGIN;
        if (ctx.y - needed < bottom) {
            // crear nueva página con el mismo encabezado
            endPage(ctx);
            PageCtx newCtx = startPage(ctx.doc, ctx.headerTitle);
            ctx.page = newCtx.page;
            ctx.cs = newCtx.cs;
            ctx.y = newCtx.y;
            ctx.contentWidth = newCtx.contentWidth;
        }
    }

    private void drawKeyValue(PageCtx ctx, String label, String value, float fontSize) throws Exception {
        if (value == null) value = "--";
        float leading = fontSize * LEADING_FACTOR;
        ensureSpace(ctx, leading);
        // Etiqueta
        ctx.cs.beginText();
        ctx.cs.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
        ctx.cs.newLineAtOffset(MARGIN, ctx.y);
        ctx.cs.showText(label + ": ");
        ctx.cs.endText();
        // Valor
        float labelWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(label + ": ") / 1000f * fontSize;
        ctx.cs.beginText();
        ctx.cs.setFont(PDType1Font.HELVETICA, fontSize);
        ctx.cs.newLineAtOffset(MARGIN + labelWidth, ctx.y);
        ctx.cs.showText(value);
        ctx.cs.endText();
        ctx.y -= leading;
    }

    private void drawSectionTitle(PageCtx ctx, String title) throws Exception {
        float fontSize = 12f;
        float leading = fontSize * LEADING_FACTOR;
        ensureSpace(ctx, leading + 4);
        ctx.cs.beginText();
        ctx.cs.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
        ctx.cs.newLineAtOffset(MARGIN, ctx.y);
        ctx.cs.showText(title);
        ctx.cs.endText();
        ctx.y -= leading * 0.8f;
    }

    private void drawParagraph(PageCtx ctx, String text, float fontSize) throws Exception {
        if (text == null) text = "";
        text = text.replace("\r", "");
        float leading = fontSize * LEADING_FACTOR;
        String[] paragraphs = text.split("\n");
        for (String para : paragraphs) {
            String[] words = para.trim().split("\\s+");
            StringBuilder line = new StringBuilder();
            for (String word : words) {
                String candidate = line.length() == 0 ? word : line + " " + word;
                float w = PDType1Font.HELVETICA.getStringWidth(candidate) / 1000f * fontSize;
                if (w <= ctx.contentWidth) {
                    line = new StringBuilder(candidate);
                } else {
                    ensureSpace(ctx, leading);
                    ctx.cs.beginText();
                    ctx.cs.setFont(PDType1Font.HELVETICA, fontSize);
                    ctx.cs.newLineAtOffset(MARGIN, ctx.y);
                    ctx.cs.showText(line.toString());
                    ctx.cs.endText();
                    ctx.y -= leading;
                    line = new StringBuilder(word);
                }
            }
            if (line.length() > 0) {
                ensureSpace(ctx, leading);
                ctx.cs.beginText();
                ctx.cs.setFont(PDType1Font.HELVETICA, fontSize);
                ctx.cs.newLineAtOffset(MARGIN, ctx.y);
                ctx.cs.showText(line.toString());
                ctx.cs.endText();
                ctx.y -= leading;
            }
            ctx.y -= leading * 0.35f;
        }
    }

    public byte[] generarPdfDesdeSesion(SesionClinicaResponse sesion) {
        try (PDDocument doc = new PDDocument()) {
            String titulo = "Sesión clínica" + (sesion.getId() != null ? (" - ID " + sesion.getId()) : "");
            PageCtx ctx = startPage(doc, titulo);

            // Metadatos
            drawKeyValue(ctx, "Número sesión", String.valueOf(sesion.getNumeroSesion()), 12f);
            drawKeyValue(ctx, "Fecha", sesion.getFecha() == null ? "" : sesion.getFecha().toString(), 12f);
            drawKeyValue(ctx, "Asistencia", sesion.getAsistencia() == null ? "" : sesion.getAsistencia().name(), 12f);
            drawKeyValue(ctx, "Duración (min)", sesion.getDuracionMinutos() == null ? "" : sesion.getDuracionMinutos().toString(), 12f);

            // Secciones
            drawSectionTitle(ctx, "Descripción");
            drawParagraph(ctx, sesion.getDescripcion(), 11f);

            drawSectionTitle(ctx, "Observaciones");
            drawParagraph(ctx, sesion.getObservaciones(), 11f);

            endPage(ctx);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Error generando PDF", ex);
        }
    }

    public byte[] generarPdfDesdeEntrevista(EntrevistaInicialResponse entrevista) {
        try (PDDocument doc = new PDDocument()) {
            PageCtx ctx = startPage(doc, "Entrevista inicial");

            drawSectionTitle(ctx, "Motivo de consulta");
            drawParagraph(ctx, entrevista.getMotivoConsulta(), 12f);

            drawSectionTitle(ctx, "Antecedentes");
            drawParagraph(ctx, entrevista.getAntecedentes(), 11f);

            drawSectionTitle(ctx, "Observaciones");
            drawParagraph(ctx, entrevista.getObservaciones(), 11f);

            endPage(ctx);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de entrevista", e);
        }
    }

    public byte[] generarPdfDesdeConsentimiento(ConsentimientoInformadoResponse consentimiento) {
        try (PDDocument doc = new PDDocument()) {
            PageCtx ctx = startPage(doc, "Consentimiento informado");

            drawSectionTitle(ctx, "Alcance");
            drawParagraph(ctx, consentimiento.getAlcance(), 12f);

            drawSectionTitle(ctx, "Riesgos y beneficios");
            drawParagraph(ctx, consentimiento.getRiesgos(), 11f);

            drawSectionTitle(ctx, "Aceptación");
            drawParagraph(ctx, consentimiento.getAceptacion(), 11f);

            endPage(ctx);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.save(os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de consentimiento", e);
        }
    }

    // (intencionalmente sin métodos legacy no usados)
}
