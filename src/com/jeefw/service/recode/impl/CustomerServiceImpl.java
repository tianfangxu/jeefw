package com.jeefw.service.recode.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.CustomerDao;
import com.jeefw.model.recode.CustomerEntity;
import com.jeefw.model.recode.param.CustomerModel;
import com.jeefw.service.recode.CustomerService;

import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.MD5;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Resource
	CustomerDao customerDao;

	@Override
	public JqGridPageView<CustomerEntity> getCustomerByCondition(
			CustomerModel model) {
		// TODO Auto-generated method stub
		return customerDao.getCustomerByCondition(model);
	}

	@Override
	public void saveProperty(CustomerModel model) {
		CustomerEntity entity = new CustomerEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getUserName().toString());
		customerDao.persist(entity);
		
	}

	@Override
	public String updateProperty(CustomerModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		CustomerEntity entity = customerDao.get(model.getId());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
			BeanUtils.copyProperties(model, entity);
			entity.setUpdatetime(DateUnit.getTime14());
			entity.setUpdateuser(model.getLoginuser().getId().toString());
			customerDao.merge(entity);
			return "修改成功";
		}
		return "未查询到数据";
	}

	@Override
	public String deleteProperty(CustomerModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		if(model.getId().indexOf(",") != -1){
			String[] strs = model.getId().split(",");
			for (String id : strs) {
				customerDao.deleteEntity(id,model.getLoginuser().getId().toString());
			}
		}else{
			customerDao.deleteEntity(model.getId(),model.getLoginuser().getId().toString());
		}
		return "删除成功";
	}

}
