package ao.com.angotech.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class EmailService {

    public final String apiKey;

    public EmailService(String apikey) {
        this.apiKey = apikey;
    }

    public void enviarComAnexo(String destinatario, String assunto, String corpo, String caminhoAnexo) {
        Email from = new Email(System.getenv("EMAIL_FROM"));
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", corpo);

        Mail mail = new Mail(from, assunto, to, content);

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(caminhoAnexo));
            Attachments attachments = new Attachments();
            attachments.setContent(Base64.getEncoder().encodeToString(bytes));
            attachments.setType("application/pdf");
            attachments.setFilename(Paths.get(caminhoAnexo).getFileName().toString());
            attachments.setDisposition("attachment");
            mail.addAttachments(attachments);

            SendGrid sendGrid = new SendGrid(apiKey);
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
