package com.jeefw.dao.recode;

import com.jeefw.model.recode.CustomerEntity;
import com.jeefw.model.recode.param.CustomerModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface CustomerDao extends Dao<CustomerEntity> {

	JqGridPageView<CustomerEntity> getCustomerByCondition(CustomerModel model);

	void deleteEntity(String id, String string);

}
