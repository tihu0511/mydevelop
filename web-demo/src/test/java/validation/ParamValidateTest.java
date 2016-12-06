package validation;

import com.alibaba.fastjson.JSON;
import com.hsq.component.busi.facade.Response;
import org.hsq.wjg.demo.validation.TestValidationService;
import org.hsq.wjg.demo.validation.ValidatePojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wjg on 2016/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ParamValidateTest {
    @Autowired
    private TestValidationService validationService;

    @Test
    public void test() {
        Response response = validationService.test(new ValidatePojo());
        System.out.println("\n======================>>>>>>>>>>>>>>>>>>");
        System.out.println(JSON.toJSONString(response));
    }
}
