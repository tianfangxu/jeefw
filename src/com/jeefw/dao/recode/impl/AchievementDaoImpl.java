package com.jeefw.dao.recode.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.recode.AchievementDao;
import com.jeefw.model.recode.AchievementEntity;
import com.jeefw.model.recode.param.AchievementModel;

import core.dao.BaseDao;
import core.support.BaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.DateUnit;
import core.util.StringUnit;
@Repository
public class AchievementDaoImpl extends BaseDao<AchievementEntity> implements AchievementDao {

	public AchievementDaoImpl() {
		super(AchievementEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JqGridPageView<AchievementModel> getBudgetByCondition(
			AchievementModel model) {
		JqGridPageView<AchievementModel> result = new JqGridPageView<AchievementModel>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where t.deleteflg = '0' ");
		
		if(!StringUnit.isNullOrEmpty(model.getBuild())){
			sb.append(" and t.build = '"+model.getBuild()+"' ");
		}
		
		if(!StringUnit.isNullOrEmpty(model.getYear())){
			sb.append(" and t.year = '"+model.getYear()+"' ");
		}
		
		if(!StringUnit.isNullOrEmpty(model.getId())){
			sb.append(" and t.id = '"+model.getId()+"' ");
		}
		
		//等于查询
		if(model.getEqparam() != null){
			AchievementModel eqmodel = (AchievementModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getBuildname())){
				sb.append(" and b.name = '"+eqmodel.getBuildname()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			AchievementModel likemodel = (AchievementModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getBuildname())){
				sb.append(" and b.name like '%"+likemodel.getBuildname()+"%' ");
			}
		}
		
		Query query = session.createSQLQuery("select t.id,t.year ,t.build,t.type ,t.serialno,t.month,"
				+ "t.property,t.fixedparking,t.tempparking,t.advertising,"
				+ "t.electricin ,t.water,t.electricout,t.gas  ,t.stationery ,t.communication,t.drinkwater ,t.doorplate  ,t.decorate,t.cleanser,t.afforestation,t.ppe  ,"
				+ "t.trashcleaning,t.emergencymaterial ,t.wallwashing,t.alarmservice,t.pestcontrol,t.sewerage,t.maintenance,t.office,"
				+ "t.other,b.name buildname,t.rest,t.servicing,t.rent,t.waterin"
				
				+ ",t.propertyps,t.fixedparkingps,t.tempparkingps,t.advertisingps,"
				+ "t.electricinps ,t.waterps,t.electricoutps,t.gasps  ,t.stationeryps ,t.communicationps,t.drinkwaterps ,t.doorplateps  ,"
				+ "t.decorateps,t.cleanserps,t.afforestationps,t.ppeps  ,"
				+ "t.trashcleaningps,t.emergencymaterialps ,t.wallwashingps,t.alarmserviceps,t.pestcontrolps,t.sewerageps,t.maintenanceps,t.officeps,"
				+ "t.otherps,t.restps,t.servicingps,t.rentps,t.waterinps"
				
				+ " from t_achievement t left join m_build b on t.build = b.id "+ sb.toString())
				.addScalar("id", StandardBasicTypes.STRING)
				.addScalar("buildname", StandardBasicTypes.STRING)
				.addScalar("year" , StandardBasicTypes.STRING)
				.addScalar("month" , StandardBasicTypes.STRING)
				.addScalar("build", StandardBasicTypes.STRING)
				.addScalar("type" , StandardBasicTypes.STRING)
				.addScalar("serialno", StandardBasicTypes.STRING)
				.addScalar("property", StandardBasicTypes.STRING)
				.addScalar("fixedparking", StandardBasicTypes.STRING)
				.addScalar("tempparking", StandardBasicTypes.STRING)
				.addScalar("advertising", StandardBasicTypes.STRING)
				.addScalar("electricin" , StandardBasicTypes.STRING)
				.addScalar("waterin", StandardBasicTypes.STRING)
				.addScalar("water", StandardBasicTypes.STRING)
				.addScalar("electricout", StandardBasicTypes.STRING)
				.addScalar("gas"  , StandardBasicTypes.STRING)
				.addScalar("stationery" , StandardBasicTypes.STRING)
				.addScalar("communication", StandardBasicTypes.STRING)
				.addScalar("drinkwater" , StandardBasicTypes.STRING)
				.addScalar("doorplate"  , StandardBasicTypes.STRING)
				.addScalar("decorate", StandardBasicTypes.STRING)
				.addScalar("cleanser", StandardBasicTypes.STRING)
				.addScalar("afforestation", StandardBasicTypes.STRING)
				.addScalar("ppe"  , StandardBasicTypes.STRING)
				.addScalar("trashcleaning", StandardBasicTypes.STRING)
				.addScalar("emergencymaterial" , StandardBasicTypes.STRING)
				.addScalar("wallwashing", StandardBasicTypes.STRING)
				.addScalar("alarmservice", StandardBasicTypes.STRING)
				.addScalar("pestcontrol", StandardBasicTypes.STRING)
				.addScalar("sewerage", StandardBasicTypes.STRING)
				.addScalar("maintenance", StandardBasicTypes.STRING)
				.addScalar("office"  , StandardBasicTypes.STRING)
				.addScalar("other", StandardBasicTypes.STRING)
				.addScalar("rest", StandardBasicTypes.STRING)
				.addScalar("servicing", StandardBasicTypes.STRING)
				.addScalar("rent", StandardBasicTypes.STRING)
				//备注
				.addScalar("propertyps", StandardBasicTypes.STRING)
				.addScalar("fixedparkingps", StandardBasicTypes.STRING)
				.addScalar("tempparkingps", StandardBasicTypes.STRING)
				.addScalar("advertisingps", StandardBasicTypes.STRING)
				.addScalar("electricinps" , StandardBasicTypes.STRING)
				.addScalar("waterinps", StandardBasicTypes.STRING)
				.addScalar("waterps", StandardBasicTypes.STRING)
				.addScalar("electricoutps", StandardBasicTypes.STRING)
				.addScalar("gasps"  , StandardBasicTypes.STRING)
				.addScalar("stationeryps" , StandardBasicTypes.STRING)
				.addScalar("communicationps", StandardBasicTypes.STRING)
				.addScalar("drinkwaterps" , StandardBasicTypes.STRING)
				.addScalar("doorplateps"  , StandardBasicTypes.STRING)
				.addScalar("decorateps", StandardBasicTypes.STRING)
				.addScalar("cleanserps", StandardBasicTypes.STRING)
				.addScalar("afforestationps", StandardBasicTypes.STRING)
				.addScalar("ppeps"  , StandardBasicTypes.STRING)
				.addScalar("trashcleaningps", StandardBasicTypes.STRING)
				.addScalar("emergencymaterialps" , StandardBasicTypes.STRING)
				.addScalar("wallwashingps", StandardBasicTypes.STRING)
				.addScalar("alarmserviceps", StandardBasicTypes.STRING)
				.addScalar("pestcontrolps", StandardBasicTypes.STRING)
				.addScalar("sewerageps", StandardBasicTypes.STRING)
				.addScalar("maintenanceps", StandardBasicTypes.STRING)
				.addScalar("officeps"  , StandardBasicTypes.STRING)
				.addScalar("otherps", StandardBasicTypes.STRING)
				.addScalar("restps", StandardBasicTypes.STRING)
				.addScalar("servicingps", StandardBasicTypes.STRING)
				.addScalar("rentps", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(AchievementModel.class)); 
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));
		
		List list = query.list();
		
		Object cout = session.createSQLQuery(
				"select count(1) from t_achievement t left join m_build b on t.build = b.id "+ sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id, String userid) {
		Query query = this.getSession().createSQLQuery(
				"update t_achievement set deleteflg = '1' ,deleteuser = '"+userid+"',deletetime = '"+DateUnit.getTime14()+"' where ID = ?");
		query.setParameter(0, id);
		query.executeUpdate();
		
	}

}
