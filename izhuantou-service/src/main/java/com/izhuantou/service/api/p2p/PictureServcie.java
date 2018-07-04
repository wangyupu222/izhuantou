package com.izhuantou.service.api.p2p;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PictureServcie {
	/**
	 * 更新个人中心的图像
	 * @param file
	 * @param memberOID
	 * @return
	 */
	String updHeadPic(MultipartFile file,String memberOID,String picPath);
	
	/**
	 * 根据图片的OID获取图片物理地址
	 * @param picOID
	 * @return
	 */
	String findPicPath(String picOID,String picPath);
	
	/**
	 * 查看pdf图片地址
	 * @param picOIDs
	 * @param picPath
	 * @return
	 */
	List<String> findPdfPicPath(String pics,String picPath);
	/**
	 * 获取pdf路径
	 * @param agrOID
	 * @param pdfFilePath
	 * @return
	 */
	String findPdfNamePath(String agrOID);
}
