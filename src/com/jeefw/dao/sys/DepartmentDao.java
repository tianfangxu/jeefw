package com.jeefw.dao.sys;

import java.util.List;

import com.jeefw.model.sys.Department;

import core.dao.Dao;

/**
 * 部门的数据持久层的接口
 * @JC
 */
public interface DepartmentDao extends Dao<Department> {
	
	List<Department> getChildrenDepartment(String parent);

}
