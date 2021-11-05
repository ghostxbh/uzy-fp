package com.uzykj.admin.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName UzyFp
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "uzyfp")
public class UzyFp {
    private String name;
    private String email;
    private String url;
    private String version;
    private String copyright;
}
