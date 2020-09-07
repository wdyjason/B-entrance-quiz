package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Team;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Api {
    @Autowired
    private StudentService studentService;

    @GetMapping("/api/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("api/student")
    public void createStudent(@RequestParam String name) {
        studentService.createStudent(name);
    }

    @GetMapping("api/team/split")
    public List<Team> dividedTeam() throws JsonProcessingException {
        return studentService.dividedTeam();
    }

    @GetMapping("api/team")
    public List<Team> getDividedTeams() {
        return studentService.getDividedTeams();
    }

    @PostMapping("api/team/{id}")
    public ResponseEntity changeTeamName(@PathVariable Integer id, @RequestParam("name") String name) {
        if (studentService.changeTeamName(id, name)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
