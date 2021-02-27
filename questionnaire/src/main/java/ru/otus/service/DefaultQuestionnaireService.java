package ru.otus.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.otus.dao.Parser;
import ru.otus.model.Answer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@RequiredArgsConstructor
public class DefaultQuestionnaireService implements QuestionnaireService {

    private final Parser parser;
    private String fileName;

    @Override
    public void showQuestions() throws IOException {
        parser.parse(fileName).stream()
                .map(question -> "Question: " + question.getTitle() + " Answer: " + answers(question.getAnswers()))
                .forEach(System.out::println);
    }

    private String answers(List<Answer> answers) {
        return answers.stream()
                .filter(answer -> answer.getTitle() != null)
                .map(answer -> answer.getId() + ")" + answer.getTitle())
                .collect(Collectors.joining(" "));
    }
}