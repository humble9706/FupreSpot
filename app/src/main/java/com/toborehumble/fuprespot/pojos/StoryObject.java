package com.toborehumble.fuprespot.pojos;

public class StoryObject {
    private String subjectUsername;
    private String objectUsername;
    private String verb;

    public StoryObject (){}

    public StoryObject(String subjectUsername, String objectUsername, String verb) {
        this.subjectUsername = subjectUsername;
        this.objectUsername = objectUsername;
        this.verb = verb;
    }

    public String getSubjectUsername() {
        return subjectUsername;
    }

    public void setSubjectUsername(String subjectUsername) {
        this.subjectUsername = subjectUsername;
    }

    public String getObjectUsername() {
        return objectUsername;
    }

    public void setObjectUsername(String objectUsername) {
        this.objectUsername = objectUsername;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }
}
