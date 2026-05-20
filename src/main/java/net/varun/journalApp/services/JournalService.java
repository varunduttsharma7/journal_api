package net.varun.journalApp.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import net.varun.journalApp.Entities.Journal;
import net.varun.journalApp.Entities.User;
import net.varun.journalApp.repository.JournalRepository;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UsersService _usersService;

    public Journal saveEntry(@NonNull Journal journal) {
        journal.setDate(LocalDateTime.now());
        return journalRepository.save(journal);
    }

    public Collection<Journal> findAll() {
        return journalRepository.findAll();
    }

    public List<Journal> findByUser(String userName) {
        User user = _usersService.findByUserName(userName);
        List<Journal> journal = user.getUserJournals();
        return journal;
    }

    public Boolean deleteById(ObjectId id) {
        Journal journal = journalRepository.findById(id).get();
        if (journal == null) {
            return false;
        }
        journalRepository.deleteById(id);
        return false;
    }

    @Transactional
    public Boolean deleteByUserNameAndId(@PathVariable String userName, ObjectId id) {
        try {
            User user = _usersService.findByUserName(userName);
            user.getUserJournals().removeIf(x -> x.getId().equals(id));
            journalRepository.deleteById(id);
            // induce some error
            user.setPassword(null);
            _usersService.saveEntry(user);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error while deletion");
            return false;
        }

    }

    public Boolean UpdateJournal(Journal update) {
        Journal journal = journalRepository.findById(update.getId()).get();
        if (journal == null) {
            return false;
        }
        journal.setContent(update.getContent());
        journal.setTitle(update.getTitle());
        journal.setDate(LocalDateTime.now());
        journalRepository.save(journal);
        return true;
    }

    public Boolean UpdateJournalByUser(
            String userName,
            ObjectId id,
            Journal update) {
        User user = _usersService.findByUserName(userName);

        List<Journal> journals = user.getUserJournals().stream().filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        if (journals.isEmpty()) {
            return false;
        }
        Journal journal = journalRepository.findById(id).get();
        journal.setContent(update.getContent());
        journal.setTitle(update.getTitle());
        journal.setDate(LocalDateTime.now());
        journalRepository.save(journal);
        return true;
    }

    public boolean AddJournalByUser(String userName, Journal journal) {
        User user = _usersService.findByUserName(userName);
        Journal journal2 = saveEntry(journal);
        user.getUserJournals().add(journal2);
        _usersService.saveEntry(user);
        return true;
    }
}
