package main.java.pertemuan11.Jfc_Mvc.src.Tugas.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.Product;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductPdf {
    public void exportPdf(List<Product> products) {
        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.dir") + File.separator + "products_report.pdf"));

            // Open document
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("RetroGame eShop Product Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Create table
            float[] columnWidths = {5f, 20f, 12f, 8f, 15f, 15f, 15f, 15f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(5);

            // Add headers
            String[] headers = {"No", "Name", "Price", "Qty", "Category", "Genre", "Created", "Updated"};
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(210, 210, 210));
                cell.setPadding(8);
                table.addCell(cell);
            }

            // Add data
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
            int no = 1;
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            for (Product product : products) {
                // Number
                addCell(table, String.valueOf(no++), Element.ALIGN_CENTER, dataFont);

                // Name
                addCell(table, product.getName(), Element.ALIGN_LEFT, dataFont);

                // Price
                String price = currencyFormat.format(product.getPrice());
                addCell(table, price, Element.ALIGN_RIGHT, dataFont);

                // Quantity
                addCell(table, String.valueOf(product.getQuantity()), Element.ALIGN_CENTER, dataFont);

                // Category
                addCell(table, product.getCategoryName(), Element.ALIGN_LEFT, dataFont);

                // Genre
                addCell(table, product.getGenreName() != null ? product.getGenreName() : "-",
                        Element.ALIGN_LEFT, dataFont);

                // Created At
                addCell(table, product.getCreatedAt(), Element.ALIGN_CENTER, dataFont);

                // Updated At
                addCell(table, product.getUpdatedAt(), Element.ALIGN_CENTER, dataFont);
            }

            document.add(table);

            // Add footer
            Paragraph footer = new Paragraph("Generated on: " + java.time.LocalDateTime.now().toString(),
                    FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10));
            footer.setAlignment(Element.ALIGN_RIGHT);
            footer.setSpacingBefore(20);
            document.add(footer);

        } catch (DocumentException | IOException ex) {
            System.err.println("Error generating PDF: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void addCell(PdfPTable table, String content, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "-", font));
        cell.setHorizontalAlignment(alignment);
        cell.setPadding(5);
        table.addCell(cell);
    }
}