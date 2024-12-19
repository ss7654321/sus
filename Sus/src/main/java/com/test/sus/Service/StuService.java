package com.test.sus.Service;

import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;

import java.util.List;

public interface StuService {
    public String login(String stu_name, String stu_pass);

    String register(Student student);

    void forget(Student student);

    List<StudentExam> getScore(Integer student_id);
}
