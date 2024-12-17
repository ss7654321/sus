package com.test.sus.Controller;

import com.test.sus.Pojo.Result;
import com.test.sus.Pojo.Student;
import com.test.sus.Service.Impl.StuServiceImpl;
import com.test.sus.Service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StuController {
    private static final Logger log = LoggerFactory.getLogger(StuController.class);
    @Autowired
    private StuService stuService;

    @PostMapping("/login")
    public Result login(@RequestBody String username, @RequestBody String password) {
        log.info("得到的用户名和密码：{} {}", username, password);
        stuService.login(username,password);
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody Student student) {
        return Result.success(stuService.register(student));
    }
}
