package ru.gecec.chgkbot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.gecec.chgkbot.parser.model.Championship;
import ru.gecec.chgkbot.parser.storage.ChampionshipRepository;
import ru.gecec.chgkbot.parser.storage.MongoConfiguration;

import java.io.IOException;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class ParserTest {
    @Autowired
    ChampionshipRepository repository;

    @Test
    public void testHtmlParse(){
        Document html = null;
        try {
            html = Jsoup.connect("https://db.chgk.info/random/types1/complexity1/limit3").get(); //+random number + limitNN

        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = html.title();
        Elements questions = html.select("div.random_question");

        for (Element question : questions){

        }

        //assertEquals("База вопросов \"Что? Где? Когда?\".", title);
    }

    @Test
    public void testTextParse(){
        DbInfoParser parser = new DbInfoParser();
        try {
            Championship result = parser.parse("D:\\PROJECTS\\chgkbot\\base\\db\\baza\\12koll10.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveToMongo(){
        DbInfoParser parser = new DbInfoParser();
        Championship result = null;
        try {
            result = parser.parse("D:\\PROJECTS\\chgkbot\\base\\db\\baza\\12koll10.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.setId(UUID.randomUUID().toString());

        repository.save(result);
    }
}
