package org.hsq.wjg.demo.web.beecloud;

import cn.beecloud.BCEumeration;
import cn.beecloud.BCPay;
import cn.beecloud.BeeCloud;
import cn.beecloud.bean.TransferParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wujigang on 2016/10/10.
 */
@Controller
@RequestMapping("/beecloud")
public class BeeCloudController {
    private static final String APP_ID = "1ab7992b-26b6-4f1c-b007-02f6c325444c";
    private static final String TEST_SECRET = "d804c37f-26e2-4550-a0cb-947e0e4abb6a";
    private static final String APP_SECRET = "72e12f38-c89d-4061-bf6e-2b446ef2fe9c";
    private static final String MASTER_SECRET = "08bea2e6-223b-4dbf-96d4-3c870abee599";

    @PostConstruct
    public void init() {
        boolean isLive = true;

        if (isLive) {
            BeeCloud.registerApp(APP_ID, null, APP_SECRET, MASTER_SECRET);
        } else {
            BeeCloud.registerApp(APP_ID, TEST_SECRET, null, null);
            BeeCloud.setSandbox(true);
        }
    }

    @RequestMapping("transfer")
    public void transfer(HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        TransferParameter param = new TransferParameter();
        param.setChannel(BCEumeration.TRANSFER_CHANNEL.ALI_TRANSFER);
        param.setChannelUserId("990034346@qq.com");
        param.setChannelUserName("吴集刚");
        param.setTotalFee(1);
        param.setDescription("支付宝单笔打款！");
        param.setAccountName("上海抚企信息科技有限公司");
        param.setTransferNo("transfer" + sdf.format(new Date()));
        try {
            String url = BCPay.startTransfer(param);
            System.out.println(url);
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
