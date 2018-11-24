package com.jeefw.dao.recode;

import com.jeefw.model.recode.PropertyEntity;
import com.jeefw.model.recode.param.PropertyModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface PropertyDao  extends Dao<PropertyEntity>{

	JqGridPageView<PropertyModel> getPropertyByCondition(PropertyModel model);

	void deleteEntity(String id, String string);

}
