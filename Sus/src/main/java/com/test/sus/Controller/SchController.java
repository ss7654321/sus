package com.test.sus.Controller;

import com.test.sus.Pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchController {
    @GetMapping("/test")
    public Result index() {
        return Result.success("holle");
    }
}
