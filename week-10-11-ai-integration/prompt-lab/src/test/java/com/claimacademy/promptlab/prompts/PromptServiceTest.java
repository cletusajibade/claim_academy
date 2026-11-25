package com.claimacademy.promptlab.prompts;

import com.claimacademy.promptlab.ai.AiAnswer;
import com.claimacademy.promptlab.ai.AiClient;
import com.claimacademy.promptlab.ai.AiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * No API key and no network: AiClient is an interface, so a lambda can play Claude.
 * This is how you test AI features: fake the model, test YOUR code around it.
 */
class PromptServiceTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private static AiClient replyWith(String text) {
        return request -> new AiAnswer(text, "end_turn", 10, 5);
    }

    @Test
    void fewShotSendsExamplesAsEarlierTurnsAndTheRealQuestionLast() {
        AtomicReference<AiRequest> sent = new AtomicReference<>();
        AiClient fake = request -> {
            sent.set(request);
            return new AiAnswer("feat: x", "end_turn", 1, 1);
        };

        new PromptService(fake, mapper).commitMessage("Added a search box");

        AiRequest request = sent.get();
        assertThat(request.turns()).hasSize(Prompts.COMMIT_MESSAGE_EXAMPLES.length * 2 + 1);
        assertThat(request.turns().get(1).role()).isEqualTo(AiRequest.Role.ASSISTANT);
        assertThat(request.turns().get(request.turns().size() - 1).text()).isEqualTo("Added a search box");
    }

    @Test
    void chainOfThoughtKeepsOnlyTheCode() {
        String reply = "<plan>Loop over chars.</plan>\n<code>\nint count() { return 0; }\n</code>";
        var service = new PromptService(replyWith(reply), mapper);

        assertThat(service.generateMethod("count vowels").code()).isEqualTo("int count() { return 0; }");
    }

    @Test
    void chainOfThoughtWithoutCodeTagIsAnError() {
        var service = new PromptService(replyWith("Sure! Here is some code..."), mapper);

        assertThatThrownBy(() -> service.generateMethod("count vowels"))
                .isInstanceOf(AiOutputException.class);
    }

    @Test
    void jsonInsideFencesIsStillParsed() {
        String reply = "```json\n{\"title\": \"Pay rent\", \"priority\": \"high\", \"dueDate\": null}\n```";
        var service = new PromptService(replyWith(reply), mapper);

        TaskDraft draft = service.extractTask("pay rent asap");

        assertThat(draft.title()).isEqualTo("Pay rent");
        assertThat(draft.priority()).isEqualTo("HIGH");
        assertThat(draft.dueDate()).isNull();
    }

    @Test
    void notJsonIsRejected() {
        var service = new PromptService(replyWith("I think you should pay your rent."), mapper);

        assertThatThrownBy(() -> service.extractTask("pay rent"))
                .isInstanceOf(AiOutputException.class)
                .hasMessageContaining("not valid JSON");
    }

    @Test
    void unknownPriorityIsRejected() {
        String reply = "{\"title\": \"Pay rent\", \"priority\": \"SUPER\", \"dueDate\": null}";
        var service = new PromptService(replyWith(reply), mapper);

        assertThatThrownBy(() -> service.extractTask("pay rent"))
                .isInstanceOf(AiOutputException.class)
                .hasMessageContaining("priority");
    }
}
