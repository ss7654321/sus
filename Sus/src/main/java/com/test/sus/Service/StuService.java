package com.test.sus.Service;

import com.test.sus.Pojo.Student;

public interface StuService {
    public String login(String stu_name, String stu_pass);

    String register(Student student);
}
