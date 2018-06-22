package com.izhuantou.service.impl.p2p;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.FileInfo;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.p2p.PictureServcie;

@Service("pictureServcie")
public class PictureServcieImpl implements PictureServcie {
	private static final Logger logger = LoggerFactory.getLogger(PictureServcieImpl.class);
	@Autowired
	private FileInfoMapper fileInfoMapper;
	@Autowired
	private MemberMemberMapper memberMapper;

	@Override
	public String updHeadPic(MultipartFile file, String memberOID, String picPath) {
		try {
			if (file != null && StringUtil.isNotEmpty(memberOID)) {
				String imageFolder = DateUtils.dayTimestamp().toString();

				// 定义临时路径用来判断指定文件夹是否存在
				String tempPath = picPath + imageFolder;
				// 判断对应的文件夹是否存在
				File createMkdir = new File(tempPath);
				if (!createMkdir.exists()) {
					createMkdir.mkdirs();
				}
				// 获取图片的原始名称
				String filename = file.getOriginalFilename();
				// 新图片的名称
				Long nowTime = new Date().getTime();
				String newfilename = nowTime.toString();
				String suffix = filename.substring(filename.lastIndexOf("."));
				// 新图片
				File newImg = new File(tempPath + File.separator + newfilename + suffix);
				// 写入磁盘
				file.transferTo(newImg);

				FileInfo fileinfo = new FileInfo();
				String oid = StringUtil.getUUID();
				fileinfo.setOID(oid);
				fileinfo.setSource("upload");
				fileinfo.setName(filename);
				fileinfo.setPhysicalName(newfilename);
				fileinfo.setRelativePath(imageFolder);
				fileinfo.setLength(file.getSize());
				int rows = fileInfoMapper.saveFileInfo(fileinfo);
				if (rows == 1) {
					MemberMember member = memberMapper.findUserByOID(memberOID);
					String oldPicIOD = member.getPicOID();
					member.setPicOID(oid);
					memberMapper.updateMemberMember(member);
					if (StringUtil.isNotEmpty(oldPicIOD)) {
						FileInfo info = fileInfoMapper.findPageByOID(oldPicIOD);
						String oldName = info.getName();
						// 根路径+文件夹+名称；
						String oldPath = picPath + File.separator + info.getRelativePath() + File.separator
								+ info.getPhysicalName() + oldName.substring(oldName.lastIndexOf("."));
						File oldFile = new File(oldPath);
						oldFile.delete();
						fileInfoMapper.deleteByPrimaryKey(info.getOID());
					}
					return "1";
				}
			}
		} catch (Exception e) {
			logger.error("updHeadPic(MultipartFile file, String memberOID)" + e.getMessage());
		}
		return null;
	}

	@Override
	public String findPicPath(String picOID, String picPath) {
		try {
			if (StringUtil.isNotEmpty(picOID)) {
				FileInfo fileInfo = fileInfoMapper.findPageByOID(picOID);
				String relativpath = fileInfo.getRelativePath();
				String name = fileInfo.getName();
				String physicalName = fileInfo.getPhysicalName();
				String path = picPath + relativpath + File.separator + physicalName
						+ name.substring(name.lastIndexOf("."));
				return path;
			}
		} catch (Exception e) {
			logger.error("updHeadPic(MultipartFile file, String memberOID,String picPath)" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> findPdfPicPath(String pics, String picPath) {
		try {
			List<String> resultlist = new ArrayList<String>();
			if (StringUtil.isNotEmpty(pics)) {
				String[] oids = pics.trim().split(",");
				List<String> list = Arrays.asList(oids);
				List<FileInfo> files = fileInfoMapper.findPageByOIDList(list);
				for (FileInfo info : files) {
					String path = picPath + info.getRelativePath() + File.separator + info.getPhysicalName() + ".png";
					resultlist.add(path);
				}
				return resultlist;
			}
		} catch (Exception e) {
			logger.error("updHeadPic(MultipartFile file, String memberOID,String picPath)" + e.getMessage());
		}
		return null;
	}

}
