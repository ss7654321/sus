package com.test.sus.Service.Impl;

import com.test.sus.Mapper.StuMapper;
import com.test.sus.Pojo.MyException;
import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;
import com.test.sus.Service.StuService;
import com.test.sus.Utils.JwtUtills;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(String userName, String password) {
        Student student = stuMapper.login(userName, password);
        if (student == null)
            return null;

        String jwt = JwtUtills.generateJwt(new HashMap<>(){{
            put("id", student.getId());
            put("type", student.getType());
            put("userName", userName);
        }
        });
        return jwt;
    }

    @Transactional
    @Override
    public String register(Student student) {
        try {
            // 1. 获取Redis中存储的验证码
            String storedCode = redisTemplate.opsForValue().get(student.getEmail());

            // 2. 检查验证码是否存在
            if (storedCode == null) {
                return "验证码已过期，请重新获取验证码";
            }

            // 3. 验证输入的验证码是否与 Redis 中的验证码匹配
            if (!storedCode.equals(student.getCode())) {
                return "验证码不正确";
            }

            // 4. 删除 Redis 中的验证码，防止重复使用
            redisTemplate.delete("verification_code:" + student.getUserName());
            if(stuMapper.getIdByName(student.getUserName())==null)
                return "用户名已经存在";
            stuMapper.register(student);
            return "注册成功";
        } catch (Exception e) {
            throw new MyException("注册失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void forget(Student student) {
        if (!Objects.equals(redisTemplate.opsForValue().get(student.getEmail()), student.getCode()))
            return;
        stuMapper.forget(student.getPassword(),student.getIdCard());
    }

    @Override
    public List<StudentExam> getScore(Integer id) {
        return stuMapper.getScore(id);
    }
}
