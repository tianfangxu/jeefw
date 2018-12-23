package com.jeefw.dao.sys.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.DepartmentDao;
import com.jeefw.model.sys.Department;

import core.dao.BaseDao;

/**
 * 部门的数据持久层的实现类
 * @JC
 */
@Repository
public class DepartmentDaoImpl extends BaseDao<Department> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}

	@Override
	public List<Department> getChildrenDepartment(String parent) {
		Session session = this.getSession();
		String sql = "from Department t where parent_departmentkey = '"+parent+"' ";
		List<Department> list = session.createQuery(sql).list();
		return list;
	}

}
