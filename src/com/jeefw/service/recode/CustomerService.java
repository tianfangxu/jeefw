package com.jeefw.service.recode;

import com.jeefw.model.recode.CustomerEntity;
import com.jeefw.model.recode.param.CustomerModel;

import core.support.JqGridPageView;

public interface CustomerService {

	JqGridPageView<CustomerEntity> getCustomerByCondition(CustomerModel model);

	void saveProperty(CustomerModel model);

	String updateProperty(CustomerModel model);

	String deleteProperty(CustomerModel model);

}
