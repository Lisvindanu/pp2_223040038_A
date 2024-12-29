package main.java.pertemuan11.Jfc_Mvc.src.latihan.view;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import main.java.pertemuan11.Jfc_Mvc.src.latihan.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPdf {
    public void exportPdf(List<User> users) {
        System.out.println(users.size());
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.dir") + File.separator + "users.pdf"));

            // Membuka dokumen
            document.open();


            float[] columnDefinitionSize = {10f, 45f, 45f};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.setWidthPercentage(100); // Menyesuaikan tabel dengan lebar halaman
            table.getDefaultCell().setBorder(1);

            // Menambahkan header tabel
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("No"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Name"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Email"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);

            // Menambahkan data ke tabel
            int no = 1;
            for (User user : users) {
                // Kolom No
                cell = new PdfPCell(new Phrase(String.valueOf(no++)));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(cell);

                // Kolom Name
                cell = new PdfPCell(new Phrase(user.getName()));
                table.addCell(cell);

                // Kolom Email dengan word wrapping
                cell = new PdfPCell(new Phrase(user.getEmail()));
                cell.setNoWrap(false); // Membiarkan teks terbungkus jika terlalu panjang
                table.addCell(cell);
            }

            // Menambahkan tabel ke dokumen
            document.add(table);

        } catch (DocumentException | IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            // Menutup dokumen untuk memastikan PDF tersimpan dengan benar
            document.close();
        }
    }
}
