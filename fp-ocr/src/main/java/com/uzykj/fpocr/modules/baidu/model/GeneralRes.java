package com.uzykj.fpocr.modules.baidu.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description baidu ocr general model
 */
@Data
public class GeneralRes {
    private Long log_id;
    private Integer direction;
    private Integer words_result_num;
    private List<WordsResult> words_result;
}

@Data
class WordsResult {
    private String words;
    private Probability probability;
}

@Data
class Probability {
    private BigDecimal average;
    private BigDecimal variance;
    private BigDecimal min;
}