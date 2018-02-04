package ru.gecec.chgkbot.parser;

import com.google.common.annotations.VisibleForTesting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import ru.gecec.chgkbot.parser.model.Championship;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {
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
}
