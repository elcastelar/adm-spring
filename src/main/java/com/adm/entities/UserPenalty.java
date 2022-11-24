package com.adm.entities;

import java.time.LocalDateTime;

public class UserPenalty {

    private User agent;

    private User offender;

    private String description;

    private LocalDateTime dateTime;

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public User getOffender() {
        return offender;
    }

    public void setOffender(User offender) {
        this.offender = offender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
