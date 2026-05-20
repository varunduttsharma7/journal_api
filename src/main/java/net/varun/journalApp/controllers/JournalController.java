package net.varun.journalApp.controllers;

import java.util.Collection;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.varun.journalApp.Entities.Journal;
import net.varun.journalApp.services.JournalService;

@RestController
@RequestMapping("journal")
public class JournalController {

    @Autowired
    private JournalService _journalService;

    // @GetMapping
    // public Collection<Journal> GetEntries() {
    // return _journalService.findAll();
    // }

    @GetMapping
    public ResponseEntity<?> GetJournalsByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<Journal> journal = _journalService.findByUser(userName);
        if (journal.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Journal>>(journal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> AddJournalByUser(@RequestBody Journal journal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (_journalService.AddJournalByUser(userName, journal)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @PutMapping("{id}")
    // public ResponseEntity<?> UpdateJournal(@PathVariable ObjectId id,
    // @RequestBody Journal journal) {
    // if (_journalService.UpdateJournal(journal)) {
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateJournalByUser(@PathVariable ObjectId id,
            @RequestBody Journal journal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (_journalService.UpdateJournalByUser(userName, id, journal)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteByUserNameAndId(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        _journalService.deleteByUserNameAndId(userName, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
