package org.hsq.wjg.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wujigang on 2016/10/10.
 */
@Controller
public class TestController {
    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return "Successful!";
    }
}
