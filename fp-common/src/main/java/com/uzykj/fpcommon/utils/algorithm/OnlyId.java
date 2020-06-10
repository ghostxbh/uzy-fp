package com.uzykj.fpcommon.utils.algorithm;

import java.util.UUID;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description
 */
public class OnlyId {

    public static String uuid(){
        return UUID.randomUUID().toString();
    }
}
