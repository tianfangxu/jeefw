package com.jeefw.service.recode.impl;

import java.util.List;

import javax.annotation.Resource;

import com.jeefw.model.sys.Department;
import com.jeefw.model.sys.param.model.BigContractModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.BuildDao;
import com.jeefw.dao.recode.PropertyDao;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.PropertyEntity;
import com.jeefw.model.recode.param.BuildModel;
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
	
	@Resource
	BuildDao buildDao;

	@Override
	public JqGridPageView<PropertyModel> getPropertyByCondition(
			PropertyModel model) {
		BuildModel cmodel = new BuildModel();
		cmodel.setLoginuser(model.getLoginuser());
		cmodel.setPage("1");
		cmodel.setRows("1000");
		List<BuildEntity> rows = buildDao.getBuildByCondition(cmodel, null).getRows();
		String ids = "";
		if(rows != null && rows.size() != 0){
			for (BuildEntity buildEntity : rows) {
				ids += "'"+buildEntity.getId()+"',";
			}
		}
		ids = ids.substring(0, ids.length()-1);
		model.setBuilds(ids);
		return PropertyDao.getPropertyByCondition(model);
	}

	@Override
	public void saveProperty(PropertyModel model) {
		PropertyEntity entity = new PropertyEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getId().toString());
		PropertyDao.persist(entity);

	}

	@Override
	public String updateProperty(PropertyModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		PropertyEntity entity = PropertyDao.get(model.getId());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
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

	@Override
	public String getPropertyJsonByCondition(BigContractModel model) {
		if(StringUtils.isEmpty(model.getBuildid())){
			return "buildid为空";
		}
		return PropertyDao.getPropertyJsonByCondition(model);
	}
}
