package com.test.sus.Mapper;

import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuMapper {
    @Select("select * from sus.student where user_name = #{userName} and password = #{password}")
    Student login(String userName, String password);

    @Insert("insert into sus.student value #{student}")
    void register(Student student);

    @Select("select id from sus.student where user_name = #{user_name}")
    Integer getIdByName(String user_name);

    @Update("UPDATE sus.student SET password = #{password} WHERE id_card = #{idCard}")
    void forget(String password ,String idCard);

    @Select("select * from sus.student s join sus.exam e on s.id = e.student_id where s.id = #{id}")
    List<StudentExam> getScore(Integer id);
}
