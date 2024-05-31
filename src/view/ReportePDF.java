/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ReportePDF {

    public static void main(String[] args) {
        String dest = "C:\\Users\\Usuario\\Desktop\\Proyectosdisenio\\New BookingHotel_Proyect2\\BookingHotel_Proyect\\reporte_ventas_itext5.pdf";
        
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();

            // Título del documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Análisis de Ventas del Primer Trimestre 2024", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Información del autor y fecha
            Font authorFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph author = new Paragraph("Autor: Juan Pérez", authorFont);
            author.setAlignment(Element.ALIGN_LEFT);
            author.setSpacingBefore(10);
            document.add(author);

            Paragraph date = new Paragraph("Fecha: 31 de Mayo de 2024", authorFont);
            date.setAlignment(Element.ALIGN_LEFT);
            date.setSpacingAfter(20);
            document.add(date);

            // Introducción
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph introductionTitle = new Paragraph("Introducción", sectionFont);
            document.add(introductionTitle);

            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph introduction = new Paragraph("Este reporte presenta un análisis detallado de las ventas "
                    + "durante el primer trimestre del año 2024, comparando los resultados con "
                    + "trimestres anteriores y proporcionando recomendaciones para mejorar las ventas.", bodyFont);
            introduction.setSpacingAfter(20);
            document.add(introduction);

            // Resumen de Ventas Mensuales
            Paragraph salesSummaryTitle = new Paragraph("Resumen de Ventas Mensuales", sectionFont);
            salesSummaryTitle.setSpacingBefore(10);
            document.add(salesSummaryTitle);

            // Tabla de ventas
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell cell;

            // Encabezados de la tabla
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            cell = new PdfPCell(new Phrase("Mes", headerFont));
            cell.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Ventas 2024", headerFont));
            cell.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Ventas 2023", headerFont));
            cell.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Variación (%)", headerFont));
            cell.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell);

            // Datos de la tabla
            table.addCell("Enero");
            table.addCell("1000");
            table.addCell("950");
            table.addCell("5.26%");

            table.addCell("Febrero");
            table.addCell("1100");
            table.addCell("1000");
            table.addCell("10.00%");

            table.addCell("Marzo");
            table.addCell("1200");
            table.addCell("1150");
            table.addCell("4.35%");

            document.add(table);

            // Conclusión
            Paragraph conclusionTitle = new Paragraph("Conclusión", sectionFont);
            conclusionTitle.setSpacingBefore(20);
            document.add(conclusionTitle);

            Paragraph conclusion = new Paragraph("El análisis de ventas del primer trimestre de 2024 muestra un incremento "
                    + "sostenido en comparación con el mismo período del año anterior. Se recomienda continuar con las estrategias actuales "
                    + "y explorar nuevas oportunidades de mercado para mantener el crecimiento.", bodyFont);
            document.add(conclusion);

            document.close();
            System.out.println("PDF creado con éxito.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
