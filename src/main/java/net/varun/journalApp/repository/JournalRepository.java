package net.varun.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.varun.journalApp.Entities.Journal;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {

}
