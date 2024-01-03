package com.example.gestiondestaches.Model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class InvoiceDataPdfExport extends AbstractPdfView {
    @Override
    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
        Font headerFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.magenta);
        HeaderFooter header = new HeaderFooter(new Phrase("All Tasks PDF View", headerFont), false);
        header.setAlignment(Element.ALIGN_CENTER);
        document.setHeader(header);

        Font dateFont = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.BLUE);

        HeaderFooter footer = new HeaderFooter(new Phrase("PDF Export Executed On : "+new Date(), dateFont), true);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);
    }

    @Override
    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {

        //download PDF with a given filename
        response.addHeader("Content-Disposition", "attachment;filename=Task.pdf");

        //read data from controller
        List<Task> list = (List<Task>) model.get("list");

        //create element
        Font titleFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD, Color.blue);
        Paragraph title = new Paragraph("ALL TASK DATA", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(20.0f);
        title.setSpacingAfter(25.0f);
        //add to document
        document.add(title);

        Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.blue);
        PdfPTable table = new PdfPTable(7);// no.of columns
        table.addCell(new Phrase("Id",tableHead));
        table.addCell(new Phrase("Title",tableHead));
        table.addCell(new Phrase("Description",tableHead));
        table.addCell(new Phrase("Category",tableHead));
        table.addCell(new Phrase("Status",tableHead));
        table.addCell(new Phrase("Priority",tableHead));
        table.addCell(new Phrase("DueDate",tableHead));


        for(Task task : list ) {
            table.addCell(task.getId().toString());
            table.addCell(task.getTitle());
            table.addCell(task.getDescription());
            table.addCell(task.getCategory());
            table.addCell(String.valueOf(task.getStatus()));
            table.addCell(String.valueOf(task.getPriority()));
            table.addCell(String.valueOf(task.getDueDate()));

        }
        //add table data to document
        document.add(table);
    }


}