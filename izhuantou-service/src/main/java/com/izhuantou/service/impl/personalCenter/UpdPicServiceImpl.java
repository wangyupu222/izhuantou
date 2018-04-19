package com.izhuantou.service.impl.personalCenter;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.vo.PictureInfo;
import com.izhuantou.service.api.personalCenter.UpdPicService;

@Service("updPicServiceImpl")
public class UpdPicServiceImpl implements UpdPicService {

    @Override
    public void updHeadPic(PictureInfo dtoFormUploadImg) {

	FileInputStream fileInputStream = (FileInputStream) dtoFormUploadImg.getSelectImg();

    }

}
