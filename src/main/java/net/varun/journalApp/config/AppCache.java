package net.varun.journalApp.config;


import net.varun.journalApp.Entities.External;
import net.varun.journalApp.repository.ExternalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        POST_API;
    }
    @Autowired
    public ExternalRepository externalRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void GetExternalUrl(){
        appCache=new HashMap<>();
        List<External> data=externalRepository.findAll();
        for (External external:data){
            appCache.put(external.getKey(),external.getValue());
        }
    }
}
