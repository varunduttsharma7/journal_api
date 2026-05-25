package net.varun.journalApp.services;

import net.varun.journalApp.externalEntities.ExternalPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalAPIService {

    @Autowired
    RestTemplate template;

    @Value("${external.api.fake}")
    public String baseUrl;

    public List<ExternalPost> getPosts(){
        ExternalPost[] posts = template.getForObject(baseUrl + "posts", ExternalPost[].class);
        return Arrays.asList(posts != null ? posts : new ExternalPost[0]);
    }
}
