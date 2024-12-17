package com.test.sus.Service.Impl;

import com.test.sus.Mapper.StuMapper;
import com.test.sus.Pojo.MyException;
import com.test.sus.Pojo.Student;
import com.test.sus.Service.StuService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Override
    public String login(String userName, String password) {
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"sw")
                .addClaims(new HashMap<String, Object>() {{
                    put("userName",userName);
                }})
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))
                .compact();

        return null;
    }

    @Transactional
    @Override
    public String register(Student student) {
        try {
            if(stuMapper.getIdByName(student.getUserName())==null)
                return "用户名已经存在";
            stuMapper.register(student);
            return "注册成功";
        } catch (Exception e) {
            throw new MyException("注册失败: " + e.getMessage());
        }
    }
}
