package net.varun.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.varun.journalApp.Entities.User;

public interface UsersRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
