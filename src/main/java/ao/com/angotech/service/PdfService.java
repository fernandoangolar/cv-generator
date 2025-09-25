package ao.com.angotech.service;

import ao.com.angotech.model.Curriculum;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfService {

    public void exportToPdf(Curriculum curriculum, String filePath) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 10, Font.BOLD);
        Font sectionFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Font textFont = new Font(Font.HELVETICA, 12);

        document.add(new Paragraph("Curriculo Profissional", titleFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(curriculum.getConteudoMarkdown()));

        document.close();
    }
}
