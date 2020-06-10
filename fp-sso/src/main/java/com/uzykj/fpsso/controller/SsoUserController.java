package com.uzykj.fpsso.controller;

import com.uzykj.fpcommon.utils.crypto.Md5;
import com.uzykj.fpcommon.utils.response.JsonResponse;
import com.uzykj.fpcommon.utils.seria.JackJson;
import com.uzykj.fpsso.enums.SsoEnum;
import com.uzykj.fpsso.pojo.SsoUser;
import com.uzykj.fpsso.service.RedisService;
import com.uzykj.fpsso.service.SsoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ghostxbh
 * @date 2020/5/27
 * @description sso api
 */
@RestController
@RequestMapping("/api/sso")
@Api(tags = "sso", produces = "all", description = "单点登录")
public class SsoUserController {
    private Logger log = Logger.getLogger(SsoUserController.class.getName());

    @Value("${md5.secret}")
    private String secret;
    @Value("${md5.version}")
    private String version;

    @Autowired
    private SsoUserService userService;
    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public JsonResponse<SsoUser> login(@RequestParam("username") String username,
                                       @RequestParam("password") String password) {
        log.info("[sso] <login> param: " + username + " : " + password);
        JsonResponse<SsoUser> response = new JsonResponse<>();
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(password)) {
            response.setCode(SsoEnum.INVALID_PARAM.getCode())
                    .setMessage(SsoEnum.INVALID_PARAM.getMsg());
            return response;
        }
        try {
            SsoUser ssoUser = userService.login(username);
            if (ssoUser == null) {
                response.setCode(SsoEnum.USER_NOT_FOUND.getCode())
                        .setMessage(SsoEnum.USER_NOT_FOUND.getMsg());
                return response;
            } else {
                String md5Pwd = Md5.md5(password);
                String setPwd = Md5.md5(md5Pwd + secret + version);
                if (!setPwd.equals(ssoUser.getPassword())) {
                    response.setCode(SsoEnum.WARONG_PASSWORD.getCode())
                            .setMessage(SsoEnum.WARONG_PASSWORD.getMsg());
                    return response;
                }
                // 去掉非必要字段
                ssoUser.setPassword(null);
                response.setCode(SsoEnum.SUCCESS.getCode())
                        .setMessage(SsoEnum.SUCCESS.getMsg())
                        .setData(ssoUser);
                // 存入redis
                String key = ssoUser.getId();
                redisService.operations.set(key, Objects.requireNonNull(JackJson.objectToJson(ssoUser)), RedisService.DEFAULT_EXPIRE, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "[sso] <login> error", e);
            response.setCode(SsoEnum.FAILED.getCode())
                    .setMessage(SsoEnum.FAILED.getMsg());
            return response;
        }
        return response;
    }

    @GetMapping("/logOut/{userId}")
    @ApiOperation("注销")
    public JsonResponse logOut(@PathVariable String userId) {
        JsonResponse response = new JsonResponse();
        log.info("[sso] <logOut> user: " + userId);
        try {
            redisService.redisTemplate.delete(userId);
            response.success(null);
        } catch (Exception e) {
            log.log(Level.WARNING, "[sso] <logOut> error", e);
            return response.fail();
        }
        return response;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public JsonResponse<SsoUser> register(@NotNull @RequestBody SsoUser user) {
        log.info("[sso] <register> body: " + user.toString());
        JsonResponse<SsoUser> response = new JsonResponse<>();
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            response.setCode(SsoEnum.ESSENT_PARAM.getCode())
                    .setMessage(SsoEnum.ESSENT_PARAM.getMsg());
            return response;
        }
        try {
            SsoUser ssoUser = userService.login(user.getName());
            if (ssoUser != null) {
                response.setCode(SsoEnum.USER_EXISTED.getCode())
                        .setMessage(SsoEnum.USER_EXISTED.getMsg());
                return response;
            } else {
                SsoUser register = userService.register(user);
                response.setCode(SsoEnum.SUCCESS.getCode())
                        .setMessage(SsoEnum.SUCCESS.getMsg())
                        .setData(register);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "[sso] <register> error", e);
            response.setCode(SsoEnum.FAILED.getCode())
                    .setMessage(SsoEnum.FAILED.getMsg());
            return response;
        }
        return response;
    }
}
