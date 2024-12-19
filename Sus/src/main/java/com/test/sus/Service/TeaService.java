package com.test.sus.Service;

import com.test.sus.Pojo.Class;
import com.test.sus.Pojo.Exam;
import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;

import java.util.List;

public interface TeaService {
    List<Class> getCla(Integer id);

    List<Student> getStudent(Integer cLassId);

    Double getScoreClass(Integer id);

    List<StudentExam> getScore(Integer id);
}
