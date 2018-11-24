package com.jeefw.service.recode.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.PropertyDao;
import com.jeefw.model.recode.PropertyEntity;
import com.jeefw.model.recode.param.PropertyModel;
import com.jeefw.service.recode.PropertyService;

import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.MD5;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	@Resource
	PropertyDao PropertyDao;
	
	@Override
	public JqGridPageView<PropertyModel> getPropertyByCondition(
			PropertyModel model) {
		// TODO Auto-generated method stub
		return PropertyDao.getPropertyByCondition(model);
	}

	@Override
	public void saveProperty(PropertyModel model) {
		PropertyEntity entity = new PropertyEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setCode(MD5.crypt(DateUnit.getTime14()));
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getUserName().toString());
		PropertyDao.persist(entity);

	}

	@Override
	public String updateProperty(PropertyModel model) {
		if(StringUtils.isEmpty(model.getCode())){
			return "id为空";
		}
		PropertyEntity entity = PropertyDao.get(model.getCode());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
			model.setCode(entity.getCode());
			BeanUtils.copyProperties(model, entity);
			entity.setUpdatetime(DateUnit.getTime14());
			entity.setUpdateuser(model.getLoginuser().getId().toString());
			PropertyDao.merge(entity);
			return "修改成功";
		}
		return "未查询到数据";
	}

	@Override
	public String deleteProperty(PropertyModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		if(model.getId().indexOf(",") != -1){
			String[] strs = model.getId().split(",");
			for (String id : strs) {
				PropertyDao.deleteEntity(id,model.getLoginuser().getId().toString());
			}
		}else{
			PropertyDao.deleteEntity(model.getId(),model.getLoginuser().getId().toString());
		}
		return "删除成功";
	}

}
