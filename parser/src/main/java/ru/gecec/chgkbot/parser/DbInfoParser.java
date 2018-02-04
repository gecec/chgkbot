package ru.gecec.chgkbot.parser;

import org.springframework.util.StringUtils;
import ru.gecec.chgkbot.parser.model.Championship;
import ru.gecec.chgkbot.parser.model.Question;
import ru.gecec.chgkbot.parser.model.Tour;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class DbInfoParser {
    private static final String CHAMPIONSHIP = "Чемпионат:";
    private static final String DATE = "Дата:";
    private static final String TOUR = "Тур";
    private static final String EDITOR = "Редактор:";
    private static final String INFO = "Инфо:";
    private static final String QUESTION = "Вопрос ";
    private static final String ANSWER = "Ответ:";
    private static final String CORRECT = "Зачет:";
    private static final String COMMENT = "Комментарий:";
    private static final String SOURCE = "Источник:";
    private static final String AUTHOR = "Автор:";

    public Championship parse(String path) throws IOException {
        Championship championship = new Championship();

        championship.setTours(new LinkedList<Tour>());
        championship.getTours().add(new Tour(Tour.DEFAULT_TOUR_NAME));
        getLastTour(championship).setQuestions(new LinkedList<Question>());

        String current = null;
        String root = null;
        boolean newQuestionFlag = true;

        for (String str : Files.readAllLines(Paths.get(path), Charset.forName("KOI8_R"))) {
            if (StringUtils.isEmpty(str)){
                continue;
            }

            if (str.startsWith(CHAMPIONSHIP)) {
                current = CHAMPIONSHIP;
                root = CHAMPIONSHIP;
            } else if (str.startsWith(DATE)) {
                current = DATE;
            } else if (str.startsWith(TOUR)) {
                current = TOUR;
                root = TOUR;
            } else if (str.startsWith(EDITOR)) {
                current = EDITOR;
            } else if (str.startsWith(INFO)) {
                current = INFO;
            } else if (str.startsWith(QUESTION)) {
                current = QUESTION;
                root = QUESTION;
                newQuestionFlag = true;
            } else if (str.startsWith(ANSWER)) {
                current = ANSWER;
            } else if (str.startsWith(CORRECT)) {
                current = CORRECT;
            } else if (str.startsWith(COMMENT)) {
                current = COMMENT;
            } else if (str.startsWith(SOURCE)) {
                current = SOURCE;
            } else if (str.startsWith(AUTHOR)) {
                current = AUTHOR;
            } else {
                if (current == null) {
                    throw new UnsupportedOperationException("Undefined current");
                }

                if (root == null) {
                    throw new UnsupportedOperationException("Undefined root");
                }

                switch (current) {
                    case CHAMPIONSHIP:
                        championship.setName(str);
                        break;
                    case DATE:
                        if (CHAMPIONSHIP.equals(root)) {
                            championship.setDate(str);
                        }
                        break;
                    case TOUR:
                        Tour tour = new Tour(str);
                        tour.setQuestions(new LinkedList<Question>());
                        championship.getTours().add(tour);
                        break;
                    case EDITOR:
                        championship.setEditors(championship.getEditors() + "\n" + str);
                        break;
                    case INFO:
                        championship.setInfo(championship.getInfo() + "\n" + str);
                        break;
                    case QUESTION:
                        if (newQuestionFlag){
                            Question question = new Question();
                            question.setQuestion(str);
                            getLastTour(championship).getQuestions().add(question);
                            newQuestionFlag = false;
                        } else {
                            Question question = getLastQuestion(championship);
                            question.setQuestion(question.getQuestion()  + "\n" + str);
                        }

                        break;
                    case ANSWER:
                        Question question = getLastQuestion(championship);
                        question.setAnswer(question.getAnswer()  + "\n" + str);
                        break;
                    case CORRECT:
                        getLastQuestion(championship).setCorrect(str);
                        break;
                    case COMMENT:
                        if (QUESTION.equals(root)){
                            getLastQuestion(championship).setComment(str);
                        }
                        break;
                    case SOURCE:
                        if (CHAMPIONSHIP.equals(root)) {
                            championship.setSource(str);
                        } else if (TOUR.equals(root)) {
                            getLastTour(championship).setSource(str);
                        } else if (QUESTION.equals(root)) {
                            getLastQuestion(championship).setSource(str);
                        } else {
                            throw new UnsupportedOperationException("Undefined ROOT");
                        }
                        break;
                    case AUTHOR:
                        if (CHAMPIONSHIP.equals(root)) {
                            championship.setAuthor(championship.getAuthor()  + "\n" + str);
                        } else if (TOUR.equals(root)) {
                            getLastTour(championship).setAuthor(str);
                        } else if (QUESTION.equals(root)) {
                            getLastQuestion(championship).setAuthor(str);
                        } else {
                            throw new UnsupportedOperationException("Undefined ROOT");
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException("Unknown type");
                }
            }
        }

        Tour defaultTour = championship.getTours().get(0);
        if (Tour.DEFAULT_TOUR_NAME.equals(defaultTour.getName()) && defaultTour.getQuestions().size() == 0){
            championship.getTours().remove(0);
        }

        return championship;
    }

    private Tour getLastTour(Championship championship) {
        return championship.getTours().get(championship.getTours().size() - 1);
    }

    private Question getLastQuestion(Championship championship) {
        return getLastTour(championship).getQuestions().get(getLastTour(championship).getQuestions().size() - 1);
    }
}
