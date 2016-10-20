import org.hsq.wjg.demo.dao.ITestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wujigang on 2016/10/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestDao {
    @Autowired
    private ITestDao testDao;

    @Test
    public void test() {
        String bankAccount = testDao.queryMemberBank(100018515);
        System.out.println(bankAccount);
    }
}
