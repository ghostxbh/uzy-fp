package com.uzykj.fpocr.modules.baidu.config;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description baidu client config
 */
@Component
public class AipOcrConfig {
    @Value("${ocr.baidu.app_id}")
    private String APPID;
    @Value("${ocr.baidu.api_key}")
    private String APIKEY;
    @Value("${ocr.baidu.secret_key}")
    private String SECRETKEY;

    private static final int TIMEOUT_HTTP = 60000;
    private static final int TIMEOUT_SOCKET = 60000;

    @Bean
    public AipOcr getAipOcr() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APPID, APIKEY, SECRETKEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(TIMEOUT_HTTP);
        // client.setSocketTimeoutInMillis(TIMEOUT_SOCKET);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", SERVER_PORT);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        return client;
    }
}
