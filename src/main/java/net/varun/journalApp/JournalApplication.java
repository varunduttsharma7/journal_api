package net.varun.journalApp;

import net.varun.journalApp.services.CronJobService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

    @Value("${app.owner.name}")
    private String NAME;

    @PostConstruct
    public void init() {
        System.out.println(NAME);
    }

    public static void main(String[] args) {

        SpringApplication.run(JournalApplication.class, args);


    }

    @Bean
    public PlatformTransactionManager ProvideTransactions(MongoDatabaseFactory databaseFactory) {
        return new MongoTransactionManager(databaseFactory);
    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

}