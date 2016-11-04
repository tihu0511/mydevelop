package dubbo;

import org.hsq.wjg.demo.dubbo.consumer.QylcPurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wujigang on 2016/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class QylcPurchaseTest {
    @Autowired
    private QylcPurchaseService purchaseService;

    @Test
    public void purchase() {
        purchaseService.purchase();
    }
}
