package com.uzykj.fpfile;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class FpFileApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("zzzz","zzzzxx");
        Object zzzz = operations.get("zzzz");
        System.out.println(zzzz.toString());
    }

}
