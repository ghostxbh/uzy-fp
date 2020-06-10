package com.uzykj.fpocr.modules.tess4j.web;

import com.uzykj.fpcommon.utils.response.JsonResponse;
import com.uzykj.fpocr.modules.baidu.model.GeneralRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghostxbh
 * @date 2020/6/4
 * @description Tess4j web api
 */
@RestController
@RequestMapping("/api/ocr/tess")
@Api("Tess4j OCR")
@Slf4j
public class Tess4jOcrController {

    @GetMapping("/general")
    @ApiOperation("通用识别")
    public JsonResponse<GeneralRes> general(@RequestParam String url) {
        JsonResponse<GeneralRes> response = new JsonResponse<>();
        try {
            log.info("[tess4j ocr] <general> req: " + url);
            response.success(null);
        } catch (Exception e) {
            log.error("[baidu ocr] <general> ", e);
            response.fail();
            return response;
        }
        return response;
    }
}
