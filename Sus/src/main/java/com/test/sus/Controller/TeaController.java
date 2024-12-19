package com.test.sus.Controller;

import com.test.sus.Pojo.Result;
import com.test.sus.Service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeaController {

    @Autowired
    private TeaService teaService;

    @GetMapping("/getClass")
    public Result getClass(@RequestParam String id){
        return Result.success(teaService.getCla(Integer.parseInt(id)));
    }

    @GetMapping("/getStudent")
    public Result getStudent(@RequestParam Integer cLassId){
        return Result.success(teaService.getStudent(cLassId));
    }

    @GetMapping("/getScoreClass")
    public Result getScoreClass(@RequestParam Integer id){
        return Result.success(teaService.getScoreClass(id));
    }

    @GetMapping("/getScaor")
    public Result getScaor(@RequestParam Integer id){
        return Result.success(teaService.getScore(id));
    }

}
