package com.jeefw.dao.recode.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.recode.BuildDao;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;
import com.jeefw.model.recode.param.MasterModel;
import com.jeefw.model.sys.Department;
import com.jeefw.model.sys.Role;
import com.jeefw.model.sys.SysUser;

import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.StringUnit;

@Repository
public class BuildDaoImpl extends BaseDao<BuildEntity> implements BuildDao{

	public BuildDaoImpl() {
		super(BuildEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JqGridPageView<BuildEntity> getBuildByCondition(BuildModel model,List<Department> listmodel) {
		JqGridPageView<BuildEntity> result = new JqGridPageView<BuildEntity>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where b.deleteflg = '0' ");


		//部门
		SysUser user = model.getLoginuser();
		String key = user.getDepartmentKey();
		Set<Role> roles = user.getRoles();
		String rolestring = "";
		for (Role role : roles) {
			rolestring += role.getRoleValue();
		}
		if(key!= null && !(key.equals("ZJB") || key.equals("WYB") ||key.equals("CWB"))){
			if(!StringUnit.isNullOrEmpty(user.getDepartmentKey()) && rolestring.indexOf("超级管理员") == -1){
				sb.append(" and d.department_key = '"+model.getLoginuser().getDepartmentKey()+"' ");
			}
		}

		//等于查询
		if(model.getEqparam() != null){
			BuildModel eqmodel = (BuildModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getName())){
				sb.append(" and b.name = '"+eqmodel.getName()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getAddress())){
				sb.append(" and b.address = '"+eqmodel.getAddress()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getManager())){
				sb.append(" and b.manager = '"+eqmodel.getManager()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and b.id = '"+eqmodel.getId()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			BuildModel likemodel = (BuildModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getName())){
				sb.append(" and b.name like '%"+likemodel.getName()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getAddress())){
				sb.append(" and b.address like '%"+likemodel.getAddress()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getManager())){
				sb.append(" and b.manager like '%"+likemodel.getManager()+"%' ");
			}
		}

		Query query = session.createSQLQuery("select b.* from  m_build b LEFT JOIN sys_user u on b.managerid = u.id  LEFT JOIN department d ON u.department_key = d.parent_departmentkey or u.department_key = d.department_key "
				+sb.toString() +" GROUP BY  b.id ").addEntity(BuildEntity.class);
		 /*session.createQuery("from BuildEntity "
				+ sb.toString());*/
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<BuildEntity> list = query.list();

		Object cout = session.createSQLQuery(
				"select count(a.id) from ( select b.id  from  m_build b LEFT JOIN sys_user u on b.managerid = u.id  LEFT JOIN department d "
				+ "ON u.department_key = d.parent_departmentkey or u.department_key = d.department_key " + sb.toString()+" GROUP BY b.id ) as a ")
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setTotalNumber((int)count);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id,String userid) {
		Query query = this.getSession().createSQLQuery(
				"update m_build set deleteflg = '1' ,deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<BuildEntity> getBuildsByManagerid(String managerId) {
		Query query = this.getSession().createQuery(
				" from BuildEntity where deleteflg = ? and managerid = ?");
		query.setParameter(0, "0");
		query.setParameter(1, managerId);
		return query.list();
	}


}
