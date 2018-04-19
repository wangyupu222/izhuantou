package com.izhuantou.service.impl.mobile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.mobile.App_version;
import com.izhuantou.dao.mobile.AppVersionMapper;
import com.izhuantou.service.api.mobile.AppVersionService;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public App_version findVersion() {
	try {
	    List<App_version> versionList = new ArrayList<>();
	    versionList = appVersionMapper.findVersion();
	    App_version version = new App_version();
	    for (App_version app_version : versionList) {

		if ("Android".equals(app_version.getName())) {
		    version.setA_version(app_version.getVersion());
		    version.setA_force(app_version.getStatus());
		    version.setA_patch(app_version.getHotUpdStatus());
		}

		if ("IOS".equals(app_version.getName())) {
		    version.setI_force(app_version.getStatus());
		}

	    }
	    return version;
	} catch (Exception e) {
	    return null;
	}

    }

}
