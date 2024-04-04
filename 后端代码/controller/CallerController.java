package com.springboot.springbootlogindemo.controller;
import com.springboot.springbootlogindemo.domain.Caller;
import com.springboot.springbootlogindemo.service.CallerService;
import com.springboot.springbootlogindemo.utils.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
@RestController
@RequestMapping("/caller")
public class CallerController {
    @Resource
    private CallerService callerService;
    @PostMapping("/check")
    public Result<Caller> loginController(@RequestParam String uname,@RequestParam String text){
        Caller caller=callerService.loginService(uname,text);
        if(caller!=null){
            return Result.success(caller,"Success!");
        }else{
            return Result.error("123","Wrong!");
        }
    }
    @PostMapping("/feedback")
    public Result<Caller> registController(@RequestBody Caller newCaller){
        Caller caller=callerService.registService(newCaller);
        if(caller!=null){
            return Result.success(caller,"反馈成功");
        }else{
            return Result.error("456","Wrong!");
        }
    }
}