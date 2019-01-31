package com.jeefw.dao.recode.impl;

import java.util.List;
import java.util.Set;

import com.jeefw.model.sys.Role;
import com.jeefw.model.sys.SysUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.recode.CustomerDao;
import com.jeefw.model.recode.CustomerEntity;
import com.jeefw.model.recode.param.CustomerModel;

import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.StringUnit;

@Repository
public class CustomerDaoImpl extends BaseDao<CustomerEntity> implements CustomerDao {

	public CustomerDaoImpl() {
		super(CustomerEntity.class);
	}

	@Override
	public JqGridPageView<CustomerEntity> getCustomerByCondition(
			CustomerModel model) {
		JqGridPageView<CustomerEntity> result = new JqGridPageView<CustomerEntity>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where c.deleteflg = '0' ");

		//区分个人与企业客户
		if(!StringUnit.isNullOrEmpty(model.getType())){
			sb.append(" and c.type = '"+model.getType()+"' ");
		}

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
			CustomerModel eqmodel = (CustomerModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getName())){
				sb.append(" and c.name = '"+eqmodel.getName()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getAddress())){
				sb.append(" and c.address = '"+eqmodel.getAddress()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getIdtype())){
				sb.append(" and c.idtype = '"+eqmodel.getIdtype()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getIdnumber())){
				sb.append(" and idnumber = '"+eqmodel.getIdnumber()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getContactname())){
				sb.append(" and contactname = '"+eqmodel.getContactname()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and c.id = '"+eqmodel.getId()+"' ");
			}

		}
		//like查询
		if(model.getLikeparam() != null){
			CustomerModel likemodel = (CustomerModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getName())){
				sb.append(" and c.name like '%"+likemodel.getName()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getAddress())){
				sb.append(" and c.address like '%"+likemodel.getAddress()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getIdtype())){
				sb.append(" and c.idtype like '%"+likemodel.getIdtype()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getIdnumber())){
				sb.append(" and c.idnumber like '%"+likemodel.getIdnumber()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getContactname())){
				sb.append(" and c.contactname like '%"+likemodel.getContactname()+"%' ");
			}
		}

		String sql = "SELECT c.* from m_customer c left JOIN sys_user u on c.createuser = u.id " +
				"LEFT JOIN department d ON u.department_key = d.parent_departmentkey or u.department_key = d.department_key"+sb.toString()
				+" group by c.id ";
		/*Query query = session.createQuery("from CustomerEntity "
				+ sb.toString());*/
		Query query = session.createSQLQuery(sql).addEntity(CustomerEntity.class);
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<CustomerEntity> list = query.list();

		String sqlCunt = "select count(a.id) from ( SELECT c.* from m_customer c left JOIN sys_user u on c.createuser = u.id " +
				"LEFT JOIN department d ON u.department_key = d.parent_departmentkey or u.department_key = d.department_key"+sb.toString()
				+" group by c.id ) as a  ";
		/*Object cout = session.createSQLQuery(
				"select count(1) from m_customer " + sb.toString())
				.uniqueResult();*/
		Object cout = session.createSQLQuery(sqlCunt).uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotalNumber((int)count);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id, String userid) {
		Query query = this.getSession().createSQLQuery(
				"update m_customer set deleteflg = '1' ,deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();

	}

}
