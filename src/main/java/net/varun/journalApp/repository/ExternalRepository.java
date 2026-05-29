package net.varun.journalApp.repository;

import net.varun.journalApp.Entities.External;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExternalRepository extends MongoRepository<External, ObjectId> {
}
