package dubbo;

import org.hsq.wjg.demo.dubbo.consumer.NimbleTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wjg on 2016/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class NimbleTest {
    @Autowired
    private NimbleTaskService taskService;

    @Test
    public void doTask() {
        taskService.doTask();
    }
}
