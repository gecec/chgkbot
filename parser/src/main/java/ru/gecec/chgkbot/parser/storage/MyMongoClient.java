package ru.gecec.chgkbot.parser.storage;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import ru.gecec.chgkbot.parser.model.Championship;

public class MyMongoClient {
    private DB db;

    public void connect(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("chgkbot");
    }

    public void insertChampionShip(Championship championship){
        DBCollection championships = db.getCollection("championship");

    }
}
