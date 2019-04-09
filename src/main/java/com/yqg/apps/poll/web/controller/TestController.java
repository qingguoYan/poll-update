package com.yqg.apps.poll.web.controller;

import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    public MsgResponse test (){
        try {
            System.out.println("test22");
            return MsgResponse.success("success",null);
        }catch (Exception e){
            return MsgResponse.error(e.getMessage());
        }
    }
}
