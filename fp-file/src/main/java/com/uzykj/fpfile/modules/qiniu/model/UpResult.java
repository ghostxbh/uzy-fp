package com.uzykj.fpfile.modules.qiniu.model;

import lombok.Data;

/**
 * @author ghostxbh
 * @date 2020/6/3
 * @description
 */
@Data
public class UpResult {

    private String fileName; //上传后的文件名
	private String originalName; //原文件名
	private long fileSize; //文件大小 单位（Byte）
	private String mimeType;
	private String suffix;//后缀名
	private String zoneName; //空间名称
	private String hash;
}
