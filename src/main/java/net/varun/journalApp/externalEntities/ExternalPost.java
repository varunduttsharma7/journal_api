package net.varun.journalApp.externalEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalPost {
    @JsonProperty("userId")
    public String idFromUser;
    public  String id;
    public  String title;
    public  String bosy;

}
