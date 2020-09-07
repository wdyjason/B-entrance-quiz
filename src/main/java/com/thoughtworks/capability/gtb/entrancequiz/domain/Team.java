package com.thoughtworks.capability.gtb.entrancequiz.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public Team() {
        this.teamMates = new ArrayList<Student>();
    }

    public Team(String teamName, List<Student> teamMates) {
        this.teamName = teamName;
        this.teamMates = teamMates;
    }

    private String teamName;
    private List<Student> teamMates;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Student> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<Student> teamMates) {
        this.teamMates = teamMates;
    }
}
