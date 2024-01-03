package com.personal.estacionamiento.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class PdfGenerator {

    public ByteArrayOutputStream getPDF() {
        String horaIngreso = "16:41";
        String patente = "BKKS76";
        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4);
            Calendar calendario = new GregorianCalendar();
            Locale localeSpanish = new Locale("es", "ES");
            String mesActual = calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localeSpanish);
            //String mes = "Febrero";
            //String funcionalidad = "Prueba";
            //String pagoA = "pagoA";
            Font fuenteTitulo = new Font();
            fuenteTitulo.setSize(20);

            Font negrita = new Font();
            negrita.setStyle(Font.BOLD);


            Chunk titulo = new Chunk("INGRESO VEHICULO");
            titulo.setUnderline(2f, -2f);

            titulo.setFont(fuenteTitulo);

            Chunk fecha = new Chunk(
                    "Chillán, " + calendario.get(Calendar.DATE) + " de " + mesActual + " de " + calendario.get(Calendar.YEAR));
            Chunk hora = new Chunk("Hora ingreso: "+horaIngreso);
            Chunk patenteIngreso = new Chunk("Patente: "+patente);
            //Paragraph parrafo = new Paragraph("Recibi de LA EMPRESA la cantidad de pesos $" + "10000"
            //        + " en concepto de pago por la funcionalidad " + funcionalidad + " correspondiente al proyecto "
            //        + "Proyecto Prueba");

            //parrafo.setLeading(5.0f, 1.0f);

            //Chunk firma = new Chunk("_____________________________________");

            //Chunk nombre = new Chunk(pagoA);
            //nombre.setFont(negrita);

            PdfPTable tabla = new PdfPTable(1);

            //PdfPCell celda0 = new PdfPCell(new Phrase(" "));
            PdfPCell celda1 = new PdfPCell(new Phrase(titulo));
            PdfPCell celda2 = new PdfPCell(new Phrase(fecha));
            PdfPCell celda3 = new PdfPCell(new Phrase(hora));
            PdfPCell celda4 = new PdfPCell(new Phrase(patenteIngreso));
            //PdfPCell celda5 = new PdfPCell(new Phrase(nombre));
            celda1.setBorder(Rectangle.NO_BORDER);
            celda2.setBorder(Rectangle.NO_BORDER);
            celda3.setBorder(Rectangle.NO_BORDER);
            celda4.setBorder(Rectangle.NO_BORDER);

            //tabla.addCell(celda0);
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            //tabla.addCell(celda5);

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
