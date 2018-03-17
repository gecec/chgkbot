package ru.gecec.chgkbot.parser.storage;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
        //(repositoryBaseClass = ChampionshipRepository.class)
public class MongoConfiguration {
    @Bean
    public MongoTemplate mongoTemplate() throws Exception{
        return new MongoTemplate(new MongoClient("localhost", 27017), "chgkbot");
    }
}
