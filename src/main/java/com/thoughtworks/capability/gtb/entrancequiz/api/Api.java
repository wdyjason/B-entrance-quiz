package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Team;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("api/team")
    public List<Team> dividedTeam() throws JsonProcessingException {
        return studentService.dividedTeam();
    }
}
