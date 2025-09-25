package ao.com.angotech;

import ao.com.angotech.model.Curriculum;
import ao.com.angotech.model.UserInput;
import ao.com.angotech.service.EmailService;
import ao.com.angotech.service.OpenAiServiceWrapper;
import ao.com.angotech.service.PdfService;
import ao.com.angotech.util.ConsoleUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        String apiKey = System.getenv("OPENAI_API_KEY");
        OpenAiServiceWrapper openAiService = new OpenAiServiceWrapper(apiKey);
        PdfService pdfService = new PdfService();

        System.out.println("🚀 Bem-vindo ao Gerador de Currículos Inteligente");

        boolean continuar = true;

        while (continuar) {
            UserInput input = new UserInput(
                ConsoleUtil.prompt("Digite seu nome completo"),
                ConsoleUtil.prompt("Digite sua área de interesse"),
                ConsoleUtil.promptList("Liste suas experiências"),
                ConsoleUtil.prompt("Digite sua formação acadêmica"),
                ConsoleUtil.promptList("Liste suas habilidades"),
                ConsoleUtil.prompt("Digite a sua cidade")
            );

            Curriculum curriculum = openAiService.gerarCurriculo(input);
            System.out.println("\nCurrículo gerado em Markdown:\n");
            System.out.println(curriculum.getConteudoMarkdown());

            System.out.println("\nO que deseja fazer agora?");
            System.out.println("1 - Exportar para PDF");
            System.out.println("2 - Exportar para Markdown (.md)");
            System.out.println("3 - Sair");

            String opcao = ConsoleUtil.prompt("Escolha uma opção (1/2/3)");

            switch (opcao) {
                case "1":
                    try {

                        String nomeArquivoBase = input.getNomeCompleto().trim().replace(" ", "_");
                        String pdfPath = "curriculo_" + nomeArquivoBase + ".pdf";

                        pdfService.exportToPdf(curriculum, pdfPath);
                        System.out.println("Currículo expostado para: " + pdfPath);

                        String enviarEmail = ConsoleUtil.prompt("Deseja enviar o curriculo por e-mail (s/n)");
                        if ( enviarEmail.equalsIgnoreCase("s") ) {
                            String destinatario = ConsoleUtil.prompt("Digite o e-mail de destino");

                            EmailService emailService = new EmailService(System.getenv("SENDGRID_API_KEY"));
                            emailService.enviarComAnexo(
                                    destinatario,
                                    "Seu Currículo Inteligente",
                                    "Segue em anexo o currículo gerado automaticamente",
                                    pdfPath
                            );

                            System.out.println("Currículo enviado com sucesso para " + destinatario);
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao gerar ou enviar PDF: " + e.getMessage());
                    }
                    break;

                case "2":

                    String nomeArquivoBase = input.getNomeCompleto().trim().replace(" ", "_");
                    String mdPath = "curriculo_" + nomeArquivoBase + ".md";

                    try (FileWriter writer = new FileWriter(mdPath)) {
                        writer.write(curriculum.getConteudoMarkdown());
                        System.out.println("📂 Currículo exportado para: " + mdPath);
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar Markdown: " + e.getMessage());
                    }
                    break;

                case "3":
                    continuar = false;
                    System.out.println("👋 Encerrando o Gerador de Currículos. Até logo!");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida, tente novamente.");

            }

            if (continuar) {
                String resposta = ConsoleUtil.prompt("Deseja gerar outro currículo? (s/n)");
                if (resposta.equalsIgnoreCase("n")) {
                    continuar = false;
                    System.out.println("👋 Encerrando o Gerador de Currículos. Até logo!");
                }
            }
        }


    }
}