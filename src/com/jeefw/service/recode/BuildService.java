package com.jeefw.service.recode;

import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;

import core.support.JqGridPageView;

public interface BuildService {

	JqGridPageView<BuildEntity> getBuildByCondition(BuildModel model);

	void saveBuild(BuildModel model);

	String updateBuild(BuildModel model);

	String deleteBuild(BuildModel model);

}
