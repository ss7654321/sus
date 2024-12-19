package com.test.sus.Service.Impl;

import com.test.sus.Mapper.TeaMapper;
import com.test.sus.Pojo.Class;
import com.test.sus.Pojo.Exam;
import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;
import com.test.sus.Service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaServiceImpl implements TeaService {
    @Autowired
    private TeaMapper teaMapper;

    @Override
    public List<Class> getCla(Integer id) {
        return teaMapper.getCla(id);
    }

    @Override
    public List<Student> getStudent(Integer cLassId) {
        return teaMapper.getStudent(cLassId);
    }

    @Override
    public Double getScoreClass(Integer id) {
        return teaMapper.getAverageScore(id);
    }

    @Override
    public List<StudentExam> getScore(Integer cLassId) {
        return teaMapper.getStudentExam(cLassId);
    }
}
