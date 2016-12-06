package org.hsq.wjg.demo.validation;

import com.hsq.component.busi.facade.FacadeFactory;
import com.hsq.component.busi.facade.Response;
import com.hsq.component.service.validation.DuplicateValid;
import org.springframework.stereotype.Service;

/**
 * Created by wjg on 2016/11/22.
 */
@Service
public class TestValidationService {
    @DuplicateValid
    public Response<Boolean> test(ValidatePojo pojo) {
        return FacadeFactory.createOkResponse();
    }
}
