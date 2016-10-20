package org.hsq.wjg.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Created by wujigang on 2016/10/13.
 */
@Controller
public class TestPageController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map map) {
        map.put("username", "huozhanbai");

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        map.put("time", sdf.format(new Date()));
        return "login";
    }
}
