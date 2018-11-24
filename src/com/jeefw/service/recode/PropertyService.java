package com.jeefw.service.recode;

import com.jeefw.model.recode.PropertyEntity;
import com.jeefw.model.recode.param.PropertyModel;

import core.support.JqGridPageView;

public interface PropertyService {

	JqGridPageView<PropertyModel> getPropertyByCondition(PropertyModel model);

	void saveProperty(PropertyModel model);

	String updateProperty(PropertyModel model);

	String deleteProperty(PropertyModel model);

}
