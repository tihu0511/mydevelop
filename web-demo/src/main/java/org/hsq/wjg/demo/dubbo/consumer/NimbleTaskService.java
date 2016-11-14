package org.hsq.wjg.demo.dubbo.consumer;

import com.mandao.asset.nimble.ws.INimbleTaskSchedulerWs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wjg on 2016/11/14.
 */
@Service
public class NimbleTaskService {
    @Autowired
    private INimbleTaskSchedulerWs nimbleTaskSchedulerWs;

    public void doTask() {
        nimbleTaskSchedulerWs.execute();
    }

}
