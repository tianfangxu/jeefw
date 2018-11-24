package com.jeefw.dao.recode.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import com.jeefw.dao.recode.MasterContractDao;
import com.jeefw.model.recode.MasterEntity;
import com.jeefw.model.recode.param.MasterModel;

import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.StringUnit;

@Repository
public class MasterContractDaoImpl extends
		BaseDao<MasterEntity> implements MasterContractDao {

	public MasterContractDaoImpl() {
		super(MasterEntity.class);
	}

	@Override
	public JqGridPageView<MasterEntity> getfirstpartyContractByCondition(
			MasterModel model) {
		JqGridPageView<MasterEntity> result = new JqGridPageView<MasterEntity>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where deleteflg = '0' ");
		
		//等于查询
		if(model.getEqparam() != null){
			MasterModel eqmodel = (MasterModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getName())){
				sb.append(" and name = '"+eqmodel.getName()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getAddress())){
				sb.append(" and address = '"+eqmodel.getAddress()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getContactname())){
				sb.append(" and contactname = '"+eqmodel.getContactname()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getAccountname())){
				sb.append(" and accountname = '"+eqmodel.getAccountname()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			MasterModel likemodel = (MasterModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getName())){
				sb.append(" and name like '%"+likemodel.getName()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getAddress())){
				sb.append(" and address like '%"+likemodel.getAddress()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getContactname())){
				sb.append(" and contactname like '%"+likemodel.getContactname()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getAccountname())){
				sb.append(" and accountname like '%"+likemodel.getAccountname()+"%' ");
			}
		}
		
		
		Query query = session.createQuery("from MasterEntity "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));
		List<MasterEntity> list = query.list();

		Object cout = session.createSQLQuery(
				"select count(1) from m_master " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		for (MasterEntity masterEntity : list) {
			masterEntity.setId(masterEntity.getCode());
		}
		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}


	@Override
	public void deletefirstpartyContract(String id,String userid) {
		Query query = this.getSession().createSQLQuery(
				"update m_master set deleteflg = '1',deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where code = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void savedata(MasterEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatadata(MasterEntity entity) {
		// TODO Auto-generated method stub
		
	}



}
