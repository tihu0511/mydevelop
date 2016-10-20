package org.hsq.wjg.demo.controller;

import org.hsq.wjg.demo.util.DoubleColorBallUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wujigang on 2016/10/12.
 */
@Controller
public class LogProduceController {
    private static final Logger logger = LoggerFactory.getLogger(LogProduceController.class);

    @RequestMapping("produceLog")
    @ResponseBody
    public String produceLog(HttpServletRequest request) {
        Integer num = Integer.valueOf(request.getParameter("num"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        for (int i = 0; i < num; i++) {
            logger.info("Lucky double color ball lottery result is : {} . at {}", DoubleColorBallUtil.lottery(), sdf.format(new Date()));
        }
        return "Finished!";
    }
}
