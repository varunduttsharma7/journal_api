package net.varun.journalApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.varun.journalApp.externalEntities.ExternalPost;
import net.varun.journalApp.services.ExternalAPIService;

@RestController
public class HealthCheckController {

    @Autowired
    public ExternalAPIService externalAPIService;

    @GetMapping("health")
    public String health_check() {
        return "Ok";
    }

    @GetMapping("posts")
    public List<ExternalPost> getPosts() {
        return externalAPIService.getPosts();
    }
}
