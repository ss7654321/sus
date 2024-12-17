package com.test.sus.Mapper;

import com.test.sus.Pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StuMapper {
    @Insert("insert into sus.student value #{}")
    void register(Student student);

    @Select("select id from sus.student where user_name = #{}")
    Integer getIdByName(String user_name);
}
