package com.jeefw.dao.recode;

import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface BuildDao extends Dao<BuildEntity>{

	JqGridPageView<BuildEntity> getBuildByCondition(BuildModel model);
	
	void deleteEntity(String id,String userid);

}
