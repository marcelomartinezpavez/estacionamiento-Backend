package com.personal.estacionamiento.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PdfGenerator {

    public ByteArrayOutputStream getPDF() {

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4);
            Calendar calendario = new GregorianCalendar();
            String mes = "Febrero";
            String funcionalidad = "Prueba";
            String pagoA = "pagoA";
            Font fuenteTitulo = new Font();
            fuenteTitulo.setSize(20);

            Font negrita = new Font();
            negrita.setStyle(Font.BOLD);


            Chunk titulo = new Chunk("RECIBO");
            titulo.setUnderline(2f, -2f);

            titulo.setFont(fuenteTitulo);

            Chunk fecha = new Chunk(
                    "Buenos Aires, " + calendario.get(Calendar.DATE) + " de " + mes + " de " + calendario.get(Calendar.YEAR));

            Paragraph parrafo = new Paragraph("Recibi de LA EMPRESA la cantidad de pesos $" + "10000"
                    + " en concepto de pago por la funcionalidad " + funcionalidad + " correspondiente al proyecto "
                    + "Proyecto Prueba");

            parrafo.setLeading(5.0f, 1.0f);

            Chunk firma = new Chunk("_____________________________________");

            Chunk nombre = new Chunk(pagoA);
            nombre.setFont(negrita);

            PdfPTable tabla = new PdfPTable(1);

            PdfPCell celda0 = new PdfPCell(new Phrase(" "));
            PdfPCell celda1 = new PdfPCell(new Phrase(titulo));
            PdfPCell celda2 = new PdfPCell(new Phrase(fecha));
            PdfPCell celda3 = new PdfPCell(parrafo);
            PdfPCell celda4 = new PdfPCell(new Phrase(firma));
            PdfPCell celda5 = new PdfPCell(new Phrase(nombre));

            tabla.addCell(celda0);
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);

            // Asignamos la variable ByteArrayOutputStream bos donde se escribirá el documento
            PdfWriter.getInstance(document, bos);
            document.open();
            document.add(tabla);
            document.close();
            // Retornamos la variable al finalizar
            return bos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
