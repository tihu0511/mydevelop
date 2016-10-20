import org.hsq.wjg.demo.lombok.LomBokDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wujigang on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestLombok {
    @Test
    public void test() {
        new LomBokDemo().test();
    }
}
