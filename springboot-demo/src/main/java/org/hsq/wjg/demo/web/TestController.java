package org.hsq.wjg.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wujigang on 2016/10/13.
 */
@RestController
public class TestController {
    @RequestMapping("test")
    public String test() {
        return "aa";
    }
}
