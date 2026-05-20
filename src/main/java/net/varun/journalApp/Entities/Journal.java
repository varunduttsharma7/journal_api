package net.varun.journalApp.Entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "journal")
@Data
public class Journal {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    @NonNull
    private String content;
    private LocalDateTime date;
}
