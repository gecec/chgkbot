package ru.gecec.chgkbot.parser.model;

import java.util.List;

public class Tour {
    public static final String DEFAULT_TOUR_NAME = "DEFAULT_TOUR";
    private String name;
    private String author;
    private String source;
    private List<Question> questions;

    public Tour() {
    }

    public Tour(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
