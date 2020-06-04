package com.uzykj.fpfile.modules.qiniu.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description qiniu api
 */
@RestController
@RequestMapping("api/oss/qiniu")
@Api("七牛oss")
public class QiniuController {

    @PostMapping("upload")
    @ApiOperation("上传")
    public void upload(@RequestParam MultipartFile file) {

    }
}
