package net.varun.journalApp.obsolete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.varun.journalApp.Entities.Journal;

@RestController
@RequestMapping("_journal")
public class JournalEntryController {

    private Map<ObjectId, Journal> entries = new HashMap<ObjectId, Journal>();

    @GetMapping
    public Collection<Journal> GetEntries() {
        return entries.values();
    }

    @GetMapping("{id}")
    public Journal GetJournalById(@PathVariable Long id) {
        return entries.get(id);
    }

    @PostMapping()
    public Boolean AddJournal(@RequestBody Journal journal) {
        entries.put(journal.getId(), journal);
        return true;
    }

    @PutMapping("{id}")
    public Boolean UpdateJournal(@PathVariable ObjectId id, @RequestBody Journal journal) {
        entries.put(id, journal);
        return true;
    }
}
