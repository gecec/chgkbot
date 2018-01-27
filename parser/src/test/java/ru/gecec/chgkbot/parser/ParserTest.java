package ru.gecec.chgkbot.parser;

import com.google.common.annotations.VisibleForTesting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void testParse(){
        Document html = null;
        try {
            html = Jsoup.connect("https://db.chgk.info/random/types1/complexity1").get(); //+random number + limitNN

        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = html.title();
        Elements questions = html.select("div.random-question");

        for (Element question : questions){

        }

        //assertEquals("База вопросов \"Что? Где? Когда?\".", title);
    }
}
