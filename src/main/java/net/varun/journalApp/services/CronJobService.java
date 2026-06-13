package net.varun.journalApp.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronJobService {

    private Integer count = 0;

    @Scheduled(fixedDelay = 5000)
    private void Greet() {
        count = count + 1;
        System.out.println("Cron Job:::::::::::::::::::::::");
        System.out.println("Hello from the Journal App " + count);
    }
}
