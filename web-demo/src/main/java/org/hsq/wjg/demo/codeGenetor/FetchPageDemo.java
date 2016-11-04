package org.hsq.wjg.demo.codeGenetor;

import com.hsq.component.http.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujigang on 2016/10/28.
 */
public class FetchPageDemo {
    public static void main(String[] args) throws IOException {
//        String page = HttpUtil.get("http://w.baofoo.net/pages/viewpage.action?pageId=3769027");
//        System.out.println(page);

        String loginUrl = "http://w.baofoo.net/dologin.action";
        Map<String, String> loginParam = new HashMap<String, String>(4);
        loginParam.put("os_username", "huozhanbai");
        loginParam.put("os_password", "wujigang472874");
        loginParam.put("login", "登录");
        loginParam.put("os_destination", "");
        String result = HttpUtil.post(loginUrl, loginParam);
        System.out.println(result);
    }
}
