package ao.com.angotech.service;

import ao.com.angotech.model.Curriculum;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfService {

    public void exportToPdf(Curriculum curriculum, String filePath) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        document.add(new Paragraph(curriculum.getConteudoMarkdown()));

        document.close();
    }
}
