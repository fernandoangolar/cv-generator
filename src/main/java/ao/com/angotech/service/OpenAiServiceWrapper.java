package ao.com.angotech.service;

import ao.com.angotech.model.Curriculum;
import ao.com.angotech.model.UserInput;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.Arrays;

public class OpenAiServiceWrapper {

    private final OpenAiService service;

    public OpenAiServiceWrapper(String apiKey) {
        this.service = new OpenAiService(apiKey, Duration.ofSeconds(30));
    }

    public Curriculum gerarCurriculo(UserInput input) {
        String prompt = String.format("""
            Gere um currículo profissional para %s na área %s.

            ## Experiências
            %s

            ## Formação
            %s

            ## Habilidades
            %s
            """,
                input.getNomeCompleto(),
                input.getAreaInteresse(),
                String.join(", ", input.getExperiencias()),
                input.getFormacao(),
                String.join(", ", input.getHabilidades()));

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4o-mini")
                .messages(Arrays.asList(
                        new ChatMessage(ChatMessageRole.USER.value(), prompt)
                ))
                .build();

        String markdown = service.createChatCompletion(request)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();

        return new Curriculum(markdown);
    }
}
