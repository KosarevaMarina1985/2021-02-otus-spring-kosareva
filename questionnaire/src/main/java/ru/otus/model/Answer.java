package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String title;

    @Override
    public String toString() {
        return String.format("%s) %s", id, title);
    }
}
