package com.jeefw.dao.recode.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.recode.BuildDao;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;
import com.jeefw.model.recode.param.MasterModel;

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
	public JqGridPageView<BuildEntity> getBuildByCondition(BuildModel model) {
		JqGridPageView<BuildEntity> result = new JqGridPageView<BuildEntity>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where deleteflg = '0' ");
		
		//等于查询
		if(model.getEqparam() != null){
			BuildModel eqmodel = (BuildModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getName())){
				sb.append(" and name = '"+eqmodel.getName()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getAddress())){
				sb.append(" and address = '"+eqmodel.getAddress()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getManager())){
				sb.append(" and manager = '"+eqmodel.getManager()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			BuildModel likemodel = (BuildModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getName())){
				sb.append(" and name like '%"+likemodel.getName()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getAddress())){
				sb.append(" and address like '%"+likemodel.getAddress()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getManager())){
				sb.append(" and manager like '%"+likemodel.getManager()+"%' ");
			}
		}
		
		Query query = session.createQuery("from BuildEntity "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));
		
		List<BuildEntity> list = query.list();
		
		Object cout = session.createSQLQuery(
				"select count(1) from m_build " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		for (BuildEntity entity : list) {
			entity.setId(entity.getCode());
		}
		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id,String userid) {
		Query query = this.getSession().createSQLQuery(
				"update m_build set deleteflg = '1' ,deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where code = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}


}
