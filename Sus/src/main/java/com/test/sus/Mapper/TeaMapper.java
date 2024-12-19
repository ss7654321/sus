package com.test.sus.Mapper;

import com.test.sus.Pojo.Class;
import com.test.sus.Pojo.Exam;
import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeaMapper {
    @Select("select * from sus.class where teacher_id = #{id}")
    List<Class> getCla(Integer id);

    @Select("select * from sus.student where class_id = #{classId}")
    List<Student> getStudent(Integer cLassId);

    @Select("SELECT AVG(score) FROM sus.exam " +
            "JOIN sus.class c ON c.id = exam.class_id " +
            "WHERE teacher_id = #{id}")
    Double getAverageScore(Integer id);

    @Select("select * from sus.exam e join sus.student s ON e.student_id = s.id where e.class_id = #{id}")
    List<StudentExam> getStudentExam(Integer id);
}
