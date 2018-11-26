package com.jeefw.dao.recode.impl;

import java.util.List;

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
		StringBuffer sb = new StringBuffer(" where deleteflg = '0' ");
		
		//区分个人与企业客户
		if(!StringUnit.isNullOrEmpty(model.getType())){
			sb.append(" and type = '"+model.getType()+"' ");
		}
		
		//等于查询
		if(model.getEqparam() != null){
			CustomerModel eqmodel = (CustomerModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getName())){
				sb.append(" and name = '"+eqmodel.getName()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getAddress())){
				sb.append(" and address = '"+eqmodel.getAddress()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getIdtype())){
				sb.append(" and idtype = '"+eqmodel.getIdtype()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getIdnumber())){
				sb.append(" and idnumber = '"+eqmodel.getIdnumber()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getContactname())){
				sb.append(" and contactname = '"+eqmodel.getContactname()+"' ");
			}
			
		}
		//like查询
		if(model.getLikeparam() != null){
			CustomerModel likemodel = (CustomerModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getName())){
				sb.append(" and name like '%"+likemodel.getName()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getAddress())){
				sb.append(" and address like '%"+likemodel.getAddress()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getIdtype())){
				sb.append(" and idtype like '%"+likemodel.getIdtype()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getIdnumber())){
				sb.append(" and idnumber like '%"+likemodel.getIdnumber()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getContactname())){
				sb.append(" and contactname like '%"+likemodel.getContactname()+"%' ");
			}
		}
		
		Query query = session.createQuery("from CustomerEntity "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));
		
		List<CustomerEntity> list = query.list();
		
		Object cout = session.createSQLQuery(
				"select count(1) from m_customer " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		for (CustomerEntity entity : list) {
			entity.setId(entity.getCode());
		}
		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id, String userid) {
		Query query = this.getSession().createSQLQuery(
				"update m_customer set deleteflg = '1' ,deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where code = ?");
		query.setParameter(0, id);
		query.executeUpdate();
		
	}

}
