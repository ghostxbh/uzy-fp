package com.uzykj.fpadmin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghostxbh
 * @date 2020/6/9
 * @description apollo api
 */
@Api("阿波罗")
@RestController
public class ApolloController {

    @ApiOperation("准备")
    @GetMapping("/ready")
    public String ready() {
        return "ok";
    }

    @ApiOperation("存活")
    @GetMapping("/live")
    public String live() {
        return "ok";
    }
}
