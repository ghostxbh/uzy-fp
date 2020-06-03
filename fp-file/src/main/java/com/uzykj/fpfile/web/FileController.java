package com.uzykj.fpfile.web;

import com.alibaba.fastjson.JSONObject;
import com.uzykj.fpcommon.utils.algorithm.OnlyId;
import com.uzykj.fpcommon.utils.file.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description
 */
@Controller
public class FileController {

    private static Logger log = Logger.getLogger(FileController.class.getName());

    @Value("${file.store-path}")
    private String storePath;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    public static void main(String[] args) {
        log.info(System.getProperty("user.dir"));
    }

    @PostMapping("/upload")
    @ApiOperation("上传")
    public String upload(@RequestParam MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            assert filename != null;
            String[] split = filename.split("\\.");
            String newName = OnlyId.uuid() + "." + split[split.length - 1];

            // 转存文件
            FileUtil.createDirectory(storePath);
            try {
                file.transferTo(new File(storePath + newName));
            } catch (IOException e) {
                log.log(Level.WARNING, "[file contro]", e);
            }

            jsonObject.put("success", true);
            jsonObject.put("filename", newName);
        }
        return jsonObject.toJSONString();
    }
}
