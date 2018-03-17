package ru.gecec.chgkbot.parser.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.gecec.chgkbot.parser.model.Championship;

import java.util.List;

public interface ChampionshipRepository extends MongoRepository<Championship, String> {
    List<Championship> findAll();
}
