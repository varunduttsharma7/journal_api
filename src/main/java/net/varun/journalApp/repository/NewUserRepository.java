package net.varun.journalApp.repository;

import lombok.extern.slf4j.Slf4j;
import net.varun.journalApp.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class NewUserRepository {

    @Autowired
    private MongoTemplate template;

    public List<User> getUsersForSentimentAnalysis() {
        try {
            Query query = new Query();
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            query.addCriteria(Criteria.where("userName").is("varun"));
            query.addCriteria(Criteria.where("roles").in(roles));
//        query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));   can also be given like this.
//            query.addCriteria(Criteria.where("roles").type(JsonSchemaObject.Type.BsonType.ARRAY));
            List<User> users = template.find(query, User.class);
            return users;
        } catch (Exception e) {
            log.error("Error for mongo template", e);
            return new ArrayList<User>();
        }

    }
}
