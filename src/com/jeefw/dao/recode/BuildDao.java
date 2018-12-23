package com.jeefw.dao.recode;

import java.util.List;

import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;
import com.jeefw.model.sys.Department;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface BuildDao extends Dao<BuildEntity>{

	JqGridPageView<BuildEntity> getBuildByCondition(BuildModel model,List<Department> list);
	
	void deleteEntity(String id,String userid);

}
