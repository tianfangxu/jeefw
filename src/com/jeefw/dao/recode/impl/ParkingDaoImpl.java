package com.jeefw.dao.recode.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.recode.ParkingDao;
import com.jeefw.model.recode.ParkingEntity;
import com.jeefw.model.recode.param.ParkingModel;

import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.StringUnit;

@Repository
public class ParkingDaoImpl extends BaseDao<ParkingEntity> implements ParkingDao  {

	public ParkingDaoImpl() {
		super(ParkingEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JqGridPageView<ParkingModel> getParkingByCondition(ParkingModel model) {
		JqGridPageView<ParkingModel> result = new JqGridPageView<ParkingModel>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where t.deleteflg = '0' ");

		if(!StringUnit.isNullOrEmpty(model.getBuild())){
			sb.append(" and t.build = '"+model.getBuild()+"' ");
		}

		//等于查询
		if(model.getEqparam() != null){
			ParkingModel eqmodel = (ParkingModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getName())){
				sb.append(" and t.name = '"+eqmodel.getName()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getUsed())){
				sb.append(" and t.used = '"+eqmodel.getUsed()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getType())){
				sb.append(" and t.type = '"+eqmodel.getType()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and t.id = '"+eqmodel.getId()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			ParkingModel likemodel = (ParkingModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getName())){
				sb.append(" and t.name like '%"+likemodel.getName()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getUsed())){
				sb.append(" and t.used like '%"+likemodel.getUsed()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getType())){
				sb.append(" and t.type like '%"+likemodel.getType()+"%' ");
			}
		}

		Query query = session.createSQLQuery("select t.id id,b.name buildname,t.build build,t.name name,t.area area,t.price price,t.used used,t.type type"
				+ " from m_parking t left join m_build b on t.build = b.id "+ sb.toString())
				.addScalar("id", StandardBasicTypes.STRING)
				.addScalar("buildname", StandardBasicTypes.STRING)
				.addScalar("build", StandardBasicTypes.STRING)
				.addScalar("name", StandardBasicTypes.STRING)
				.addScalar("area", StandardBasicTypes.STRING)
				.addScalar("price", StandardBasicTypes.STRING)
				.addScalar("used", StandardBasicTypes.STRING)
				.addScalar("type", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(ParkingModel.class));
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<ParkingModel> list = query.list();

		Object cout = session.createSQLQuery(
				"select count(1) from m_parking  t left join m_build b on t.build = b.id " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setTotalNumber((int)count);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id, String userid) {
		Query query = this.getSession().createSQLQuery(
				"update m_parking set deleteflg = '1' ,deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

}
