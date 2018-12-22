package com.jeefw.service.recode.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.BuildDao;
import com.jeefw.dao.sys.DepartmentDao;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;
import com.jeefw.model.sys.Department;
import com.jeefw.model.sys.SysUser;
import com.jeefw.service.recode.BuildService;

import core.support.JqGridPageView;
import core.util.DateUnit;

@Service
@Transactional
public class BuildServiceImpl implements BuildService {
	
	@Resource
	BuildDao buildDao;
	
	@Resource
	DepartmentDao departmentDao;

	@Override
	public JqGridPageView<BuildEntity> getBuildByCondition(BuildModel model) {
		// TODO Auto-generated method stub
		SysUser user = model.getLoginuser();
		String key = user.getDepartmentKey();
		List<Department> list = new ArrayList<Department>();
		//getchildAll(list , key);
		return buildDao.getBuildByCondition(model,list);
	}

	@Override
	public void saveBuild(BuildModel model) {
		BuildEntity entity = new BuildEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getUserName().toString());
		buildDao.persist(entity);
	}

	@Override
	public String updateBuild(BuildModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		BuildEntity entity = buildDao.get(model.getId());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
			BeanUtils.copyProperties(model, entity);
			entity.setUpdatetime(DateUnit.getTime14());
			entity.setUpdateuser(model.getLoginuser().getId().toString());
			buildDao.merge(entity);
			return "修改成功";
		}
		return "未查询到数据";
	}

	@Override
	public String deleteBuild(BuildModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		if(model.getId().indexOf(",") != -1){
			String[] strs = model.getId().split(",");
			for (String id : strs) {
				buildDao.deleteEntity(id,model.getLoginuser().getId().toString());
			}
		}else{
			buildDao.deleteEntity(model.getId(),model.getLoginuser().getId().toString());
		}
		return "删除成功";
	}
	
	//递归查询所有的下属部门
	public List<Department> getchildAll(List<Department> list,String key){
		List<Department> listtemp = departmentDao.getChildrenDepartment(key);
		list.addAll(listtemp);
		if(list != null && list.size() != 0){
			for (Department department : listtemp) {
				getchildAll(list, department.getDepartmentKey());
			}
		}
		return list;
	}

}
