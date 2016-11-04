package org.hsq.wjg.demo.dubbo.consumer;

import com.hsq.finance.qylc.regular.dto.purchase.PurchaseInDto;
import com.hsq.finance.qylc.regular.dto.purchase.PurchaseOutDto;
import com.hsq.finance.qylc.regular.ws.IPurchaseWs;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by wujigang on 2016/10/27.
 */
@Service
public class QylcPurchaseService {
    @Resource(name = "purchaseWs")
    private IPurchaseWs purchaseWs;

    public void purchase() {
        PurchaseInDto inDto = new PurchaseInDto();
        inDto.setMerchantId("100008954");
        inDto.setProductCode("");
        inDto.setMoney(new BigDecimal("100000.02"));
        PurchaseOutDto outDto = purchaseWs.purchase(inDto);
        if (outDto.isSuccess()) {
            System.out.println("申购成功");
        } else {
            System.out.println("========================>>>>>>>\n申购失败：" + outDto.getMsgCode() + " " + outDto.getRetMsg());
        }
    }
}
