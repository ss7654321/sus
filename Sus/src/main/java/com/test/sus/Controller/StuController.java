package com.test.sus.Controller;

import com.test.sus.Pojo.Result;
import com.test.sus.Pojo.Student;
import com.test.sus.Pojo.StudentExam;
import com.test.sus.Service.StuService;
import com.test.sus.Utils.JwtUtills;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StuController {

    @Autowired
    private StuService stuService;

    @PostMapping("/login")
    public Result login(@RequestBody String userName, @RequestBody String password) {

        String jwt = stuService.login(userName,password);
        if(jwt == null) {
            return Result.error("账号或者密码错误");
        }
        return Result.success(jwt);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Student student) {
        return Result.success(stuService.register(student));
    }

    @PostMapping("/forget")
    public Result forget(@RequestBody Student student) {
//        需要user_name,id_card,newPassword,code
        stuService.forget(student);
        return Result.success();
    }

    @GetMapping("/getScore")
    public Result getScore(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // 提取 JWT 令牌（去除 "Bearer " 前缀）
            String jwtToken = authorizationHeader.substring(7);

            try {
                // 解析 JWT
                Claims claims = JwtUtills.parseJwt(jwtToken);

                // 获取 JWT 中的学生 ID（假设你在 JWT 中存了 studentId）
                Integer studentId = Integer.parseInt(claims.get("studentId", String.class));

                // 获取学生成绩
                List<StudentExam> scores = stuService.getScore(studentId);

                // 返回成功结果
                return Result.success(scores);

            } catch (Exception e) {
                // 如果 JWT 无效、过期或解析失败，返回错误结果
                return Result.error("无效的或过期的 JWT 令牌: " + e.getMessage());
            }
        } else {
            // 如果请求头中没有 JWT 令牌
            return Result.error("缺少 JWT 令牌");
        }
    }

}
