package com.izhuantou.common;

import java.io.Serializable;

public class FileConfig implements Serializable {
	private static final long serialVersionUID = -2645144708651862768L;

	/**
	 * 图片存放路径
	 */
	private String imgFilePath;

	public String getImgFilePath() {
		return imgFilePath;
	}

	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}
}
