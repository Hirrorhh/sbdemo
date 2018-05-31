package com.study.demo;/**
 * Created by Hirror on 2018/5/29.
 */


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jf_hirror
 * @version basic 1.0 2018-05-29
 * @create 2018-05-29 17:12
 * @desc 测试controller
 **/
@RestController
public class HelloController {
    @Value("${content}")
    private String content;
    @RequestMapping(value = "/hello",method= RequestMethod.GET)
    public String hello(){
        System.out.println(content);
        return "测试hello";
    }
}
