package com.uzykj.fpocr.modules.baidu.service;

import com.baidu.aip.ocr.AipOcr;
import com.uzykj.fpocr.modules.baidu.model.GeneralRes;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description
 */
@Service
public class BaiduOcrService {
    @Autowired
    private AipOcr client;


    /**
     * 通用识别
     *
     * @param url
     * @return
     */
    public GeneralRes general(String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>(4);
        /**
         * 识别语言类型，默认为CHN_ENG
         * - CHN_ENG：中英文混合；
         * - ENG：英文；
         * - POR：葡萄牙语；
         * - FRE：法语；
         * - GER：德语；
         * - ITA：意大利语；
         * - SPA：西班牙语；
         * - RUS：俄语；
         * - JAP：日语；
         * - KOR：韩语；
         */
        options.put("language_type", "CHN_ENG");
        /**
         * 是否检测图像朝向，默认不检测
         * - true：检测朝向；
         * - false：不检测朝向。
         */
        options.put("detect_direction", "true");
        /**
         * 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
         */
        options.put("detect_language", "true");
        /**
         * 是否返回识别结果中每一行的置信度
         */
        options.put("probability", "true");

        /*
         // 参数为本地图片路径
         String image = "test.jpg";
         JSONObject res = client.basicAccurateGeneral(image, options);
         System.out.println(res.toString(2));

         // 参数为本地图片二进制数组
         byte[] file = readImageFile(image);
         res = client.basicAccurateGeneral(file, options);
         System.out.println(res.toString(2));
         */
        // 使用远程url
        JSONObject res = client.basicGeneralUrl(url, options);
        return com.alibaba.fastjson.JSONObject.parseObject(res.toString(), GeneralRes.class);
    }

    /**
     * 网络图片
     *
     * @param url
     * @return
     */
    public GeneralRes webImage(String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>(2);
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        JSONObject res = client.webImageUrl(url, options);
        return com.alibaba.fastjson.JSONObject.parseObject(res.toString(), GeneralRes.class);
    }
}
