package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Team;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final List<Student> dataSource = initDataSource();

    private static List<Team> teamSource;

    public List<Student> getStudents() {
        return dataSource;
    }

    public void createStudent(String name) {
        dataSource.add(new Student(dataSource.size() + 1, name));
    }

    public List<Team> dividedTeam() throws JsonProcessingException {
        initTeam();
        List<Student> shuffledStudents = shuffleList();
        int index = 0;
        for (Student student : shuffledStudents) {
            teamSource.get(index).getTeamMates().add(student);

            if (index == 5) index = 0;
            else index ++;
        }
        return teamSource;
    }

    public List<Student> shuffleList() throws JsonProcessingException {
        List<Student> toShuffle = deepCopy();

        int size = dataSource.size();

        Random random = new Random();
        for(int i = size - 1; i > 0; i --) {
            int swapIndex = random.nextInt(i);
            swapStudent(toShuffle, i , swapIndex);
        }

        return toShuffle;
    }

    public void swapStudent(List<Student> data, int indexA, int indexB) {
        Student studentA = data.get(indexA);
        Student studentB = data.get(indexB);
        Student tempA = new Student(studentA.getId(), studentA.getName());

        data.set(indexA, studentB);
        data.set(indexB, tempA);
    }

    public List<Student> deepCopy() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(dataSource);
        JavaType jt = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Student.class);
        return objectMapper.readValue(jsonString, jt);
    }

    public List<Student> initDataSource() {
        return Stream.of(new Student(1, "沈乐棋"),
                new Student(2, "徐慧慧"),
                new Student(3, "陈思聪"),
                new Student(4, "王江林"),
                new Student(5, "王登宇"),
                new Student(6, "杨思雨"),
                new Student(7, "江雨舟"),
                new Student(8, "廖燊"),
                new Student(9, "胡晓"),
                new Student(10, "但杰"),
                new Student(11, "盖迈达"),
                new Student(12, "肖美琦"),
                new Student(13, "黄云洁"),
                new Student(14, "齐瑾浩"),
                new Student(15, "刘亮亮"),
                new Student(16, "肖逸凡"),
                new Student(17, "王作文"),
                new Student(18, "郭瑞凌"),
                new Student(19, "李明豪"),
                new Student(20, "党泽"),
                new Student(21, "肖伊佐"),
                new Student(22, "贠晨曦"),
                new Student(23, "李康宁"),
                new Student(24, "马庆"),
                new Student(25, "商婕"),
                new Student(26, "余榕"),
                new Student(27, "谌哲"),
                new Student(28, "董翔锐"),
                new Student(29, "陈泰宇"),
                new Student(30, "赵允齐"),
                new Student(31, "张柯"),
                new Student(32, "廖文强"),
                new Student(33, "刘轲"),
                new Student(34, "廖浚斌"),
                new Student(35, "凌凤仪")).collect(Collectors.toList());
    }

    private void initTeam() {
        teamSource =  Stream
                .of(new Team(), new Team(), new Team(), new Team(), new Team(), new Team())
                .collect(Collectors.toList());
        for (int i = 0; i < teamSource.size(); i++) {
            teamSource.get(i).setTeamName("Team " + (i + 1));
        }
    }
}
