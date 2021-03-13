package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.model.Answer;
import ru.otus.model.Question;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultMapperServiceTest {

    @Mock
    private LocalizationService localizationService;
    @InjectMocks
    private DefaultMapperService service;

    @Test
    void mapQuestionToString() {
        when(localizationService.getMessage("answers")).thenReturn("Answers");
        Question question = Question.builder()
                .id(1)
                .correctAnswer(1)
                .title("Question1")
                .answers(asList(answer(1, "answer1"),
                        answer(2, "answer2"),
                        answer(3, "answer3")))
                .build();

        String result = service.mapQuestionToString(question);

        assertThat(result).isEqualTo("Question1 Answers: 1) answer1 2) answer2 3) answer3");
        verify(localizationService).getMessage("answers");
    }

    private Answer answer(int id, String title) {
        return Answer.builder()
                .id(id)
                .title(title)
                .build();
    }
}
