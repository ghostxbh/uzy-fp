package com.uzykj.fpocr.modules.baidu.web;

import com.uzykj.fpcommon.utils.response.JsonResponse;
import com.uzykj.fpocr.modules.baidu.model.GeneralRes;
import com.uzykj.fpocr.modules.baidu.service.BaiduOcrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description baidu ocr api
 */
@RestController
@RequestMapping("api/ocr/baidu")
@Api("百度OCR")
@Slf4j
public class BaiduOcrController {
    @Autowired
    private BaiduOcrService ocrService;


    @GetMapping("/general")
    @ApiOperation("通用识别")
    public JsonResponse<GeneralRes> general(@RequestParam String url) {
        JsonResponse<GeneralRes> response = new JsonResponse<>();
        try {
            log.info("[baidu ocr] <general> req: " + url);
            GeneralRes general = ocrService.general(url);
            if (general != null) {
                response.success(general);
            } else {
                response.success(null);
            }
        } catch (Exception e) {
            log.error("[baidu ocr] <general> ", e);
            response.fail();
            return response;
        }
        return response;
    }

    @GetMapping("/webImage")
    @ApiOperation("网络图片识别")
    public JsonResponse<GeneralRes> webImage(@RequestParam String url) {
        JsonResponse<GeneralRes> response = new JsonResponse<>();
        try {
            log.info("[baidu ocr] <webImage> req: " + url);
            GeneralRes webImage = ocrService.webImage(url);
            if (webImage != null) {
                response.success(webImage);
            } else {
                response.success(null);
            }
        } catch (Exception e) {
            log.error("[baidu ocr] <webImage> ", e);
            response.fail();
            return response;
        }
        return response;
    }
}
