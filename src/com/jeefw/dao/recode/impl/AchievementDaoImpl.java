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
import com.jeefw.model.recode.param.AchievementSumExportModel;
import com.jeefw.model.recode.param.AchievementSumResponseModel;

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
		}else{
			return null;
		}
		
		if(!StringUnit.isNullOrEmpty(model.getYear())){
			sb.append(" and t.year = '"+model.getYear()+"' ");
		}
		
		if(!StringUnit.isNullOrEmpty(model.getMonth())){
			sb.append(" and t.month = '"+model.getMonth()+"' ");
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
	
	/**
	 * 楼宇全年预算实绩
	 * @return
	 */
	@Override
	public JqGridPageView<AchievementSumResponseModel> sumToTable(AchievementModel model){
		JqGridPageView<AchievementSumResponseModel> result = new JqGridPageView<AchievementSumResponseModel>();
		Session session = this.getSession();
		StringBuffer where = new StringBuffer(" 1=1 and tb.deleteflg = '0' and ta.deleteflg = '0' ");
		if(!StringUnit.isNullOrEmpty(model.getBuild())){
			where.append(" and tb.build = '"+model.getBuild()+"' ");
		}
		
		if(!StringUnit.isNullOrEmpty(model.getYear())){
			where.append(" and tb.year = '"+model.getYear()+"' ");
		}
		String sql ="select "+
					"sst.name,sst.year,sst.dugetsum,"+
					"SUM(sst.Jan) as Jan,"+
					"SUM(sst.Feb) as Feb,"+
					"SUM(sst.Mar) as Mar,"+
					"SUM(sst.Apr) as Apr,"+
					"SUM(sst.May) as May,"+
					"SUM(sst.Jun) as Jun,"+
					"SUM(sst.Jul) as Jul,"+
					"SUM(sst.Aug) as Aug,"+
					"SUM(sst.Sept) as Sept,"+
					"SUM(sst.Oct) as Oct,  "+
					"SUM(sst.Nov) as Nov,  "+
					"SUM(sst.Dece) as Dece,"+
					"sst.build "+
					"from "+
					"("+
					"select "+
					"st.year,st.name,st.dugetsum," +
					"CASE st.month when 1 then st.incomesum else 0 end as Jan,"+
					"CASE st.month when 2 then st.incomesum else 0 end as Feb,"+
					"CASE st.month when 3 then st.incomesum else 0 end as Mar,"+
					"CASE st.month when 4 then st.incomesum else 0 end as Apr,"+
					"CASE st.month when 5 then st.incomesum else 0 end as May,"+
					"CASE st.month when 6 then st.incomesum else 0 end as Jun,"+
					"CASE st.month when 7 then st.incomesum else 0 end as Jul,"+
					"CASE st.month when 8 then st.incomesum else 0 end as Aug,"+
					"CASE st.month when 9 then st.incomesum else 0 end as Sept, "+
					"CASE st.month when 10 then st.incomesum else 0 end as Oct, "+
					"CASE st.month when 11 then st.incomesum else 0 end as Nov, "+
					"CASE st.month when 12 then st.incomesum else 0 end as Dece,"+
					"st.build "+
					"from (  "+
					"SELECT  "+
					"tb.property+tb.fixedparking+tb.tempparking+tb.advertising+tb.electricin+tb.waterin-tb.water-tb.electricout-tb.gas-tb.stationery-tb.communication-tb.drinkwater-tb."+
					"doorplate-tb.decorate-tb.cleanser-tb.afforestation-tb.ppe-tb.trashcleaning-tb.emergencymaterial-tb.wallwashing-tb.alarmservice-tb.pestcontrol-tb.sewerage-tb.maintenance-tb.office"+
					"-tb.other+tb.rent+tb.rest+tb.servicing as dugetsum,"+
					"ta.property+ta.fixedparking+ta.tempparking+ta.advertising+ta.electricin+ta.waterin-ta.water-ta.electricout-ta.gas-ta.stationery-ta.communication-ta.drinkwater-ta."+
					"doorplate-ta.decorate-ta.cleanser-ta.afforestation-ta.ppe-ta.trashcleaning-ta.emergencymaterial-ta.wallwashing-ta.alarmservice-ta.pestcontrol-ta.sewerage-ta.maintenance-ta.office"+
					"-ta.other+ta.rent+ta.rest+ta.servicing as incomesum,"+
					"ta.`year`, "+
					"ta.`month`,"+
					"ta.build,  "+
					"mb.`name`  "+
					"from t_budget tb LEFT JOIN t_achievement ta on tb.build = ta.build and tb.`year` = ta.`year` LEFT JOIN m_build mb on mb.id = tb.build "+
					"where "+where.toString()+
					") as st "+
					") as sst group by sst.build";
		Query query = session.createSQLQuery(sql)
				.addScalar("name", StandardBasicTypes.STRING)
				.addScalar("year", StandardBasicTypes.STRING)
				.addScalar("dugetsum"  , StandardBasicTypes.STRING)
				.addScalar("jan", StandardBasicTypes.STRING)
				.addScalar("feb", StandardBasicTypes.STRING)
				.addScalar("mar", StandardBasicTypes.STRING)
				.addScalar("apr", StandardBasicTypes.STRING)
				.addScalar("may", StandardBasicTypes.STRING)
				.addScalar("jun", StandardBasicTypes.STRING)
				.addScalar("jul", StandardBasicTypes.STRING)
				.addScalar("aug", StandardBasicTypes.STRING)
				.addScalar("sept", StandardBasicTypes.STRING)
				.addScalar("oct", StandardBasicTypes.STRING)
				.addScalar("nov", StandardBasicTypes.STRING)
				.addScalar("dece", StandardBasicTypes.STRING)
				.addScalar("build", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(AchievementSumResponseModel.class));
		
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));
		
		List list = query.list();
		
		String countsql = "select count(1) from t_budget";
		Object cout = session.createSQLQuery(countsql).uniqueResult();
		long count = Long.parseLong(cout.toString());
		
		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}
	
	/**
	 * 楼宇全年预算实绩明细对比
	 * @return
	 */
	@Override
	public JqGridPageView<AchievementSumExportModel> sumToExport(AchievementModel model){
		JqGridPageView<AchievementSumExportModel> result = new JqGridPageView<AchievementSumExportModel>();
		Session session = this.getSession();
		StringBuffer where = new StringBuffer(" 1=1 ");
		if(!StringUnit.isNullOrEmpty(model.getBuild())){
			where.append(" and tb.build = '"+model.getBuild()+"' ");
		}
		
		if(!StringUnit.isNullOrEmpty(model.getYear())){
			where.append(" and tb.year = '"+model.getYear()+"' ");
		}
		String sql ="select "+
					"tb.build,tb.year,(ta.month),mb.name,"+
					"tb.property,tb.fixedparking,tb.tempparking,tb.advertising,tb.electricin,tb.waterin,tb.water,tb.electricout,tb.gas,tb.stationery,tb.communication,tb.drinkwater,tb."+
					"doorplate,tb.decorate,tb.cleanser,tb.afforestation,tb.ppe,tb.trashcleaning,tb.emergencymaterial,tb.wallwashing,tb.alarmservice,tb.pestcontrol,tb.sewerage,tb.maintenance,tb.office"+
					",tb.other,tb.rent,tb.rest,tb.servicing,"+
					"sum(ta.property) sumproperty,sum(ta.fixedparking)sumfixedparking,sum(ta.tempparking)sumtempparking,sum(ta.advertising)sumadvertising,"+
					"sum(ta.electricin)sumelectricin,sum(ta.waterin)sumwaterin,sum(ta.water)sumwater,sum(ta.electricout)sumelectricout,sum(ta.gas)sumgas,"+
					"sum(ta.stationery)sumstationery,sum(ta.communication)sumcommunication,sum(ta.drinkwater)sumdrinkwater,sum(ta.doorplate)sumdoorplate,"+
					"sum(ta.decorate)sumdecorate,sum(ta.cleanser)sumcleanser,sum(ta.afforestation)sumafforestation,sum(ta.ppe)sumppe,sum(ta.trashcleaning)sumtrashcleaning,"+
					"sum(ta.emergencymaterial)sumemergencymaterial,sum(ta.wallwashing)sumwallwashing,sum(ta.alarmservice)sumalarmservice,sum(ta.pestcontrol)sumpestcontrol,"+
					"sum(ta.sewerage)sumsewerage,sum(ta.maintenance)summaintenance,sum(ta.office)sumoffice,sum(ta.other)sumother,sum(ta.rent)sumrent,sum(ta.rest)sumrest,sum(ta.servicing)sumservicing "+
					"from t_budget tb LEFT JOIN t_achievement ta on tb.build = ta.build LEFT JOIN m_build mb on mb.id = ta.build "+ 
					"where "+where.toString()+
					"GROUP BY ta.build ";
		Query query = session.createSQLQuery(sql)
				.addScalar("name", StandardBasicTypes.STRING)
				.addScalar("year", StandardBasicTypes.STRING)
				.addScalar("build", StandardBasicTypes.STRING)
				.addScalar("month", StandardBasicTypes.STRING)
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
				//合计
				.addScalar("sumproperty", StandardBasicTypes.STRING)
				.addScalar("sumfixedparking", StandardBasicTypes.STRING)
				.addScalar("sumtempparking", StandardBasicTypes.STRING)
				.addScalar("sumadvertising", StandardBasicTypes.STRING)
				.addScalar("sumelectricin" , StandardBasicTypes.STRING)
				.addScalar("sumwaterin", StandardBasicTypes.STRING)
				.addScalar("sumwater", StandardBasicTypes.STRING)
				.addScalar("sumelectricout", StandardBasicTypes.STRING)
				.addScalar("sumgas"  , StandardBasicTypes.STRING)
				.addScalar("sumstationery" , StandardBasicTypes.STRING)
				.addScalar("sumcommunication", StandardBasicTypes.STRING)
				.addScalar("sumdrinkwater" , StandardBasicTypes.STRING)
				.addScalar("sumdoorplate"  , StandardBasicTypes.STRING)
				.addScalar("sumdecorate", StandardBasicTypes.STRING)
				.addScalar("sumcleanser", StandardBasicTypes.STRING)
				.addScalar("sumafforestation", StandardBasicTypes.STRING)
				.addScalar("sumppe"  , StandardBasicTypes.STRING)
				.addScalar("sumtrashcleaning", StandardBasicTypes.STRING)
				.addScalar("sumemergencymaterial" , StandardBasicTypes.STRING)
				.addScalar("sumwallwashing", StandardBasicTypes.STRING)
				.addScalar("sumalarmservice", StandardBasicTypes.STRING)
				.addScalar("sumpestcontrol", StandardBasicTypes.STRING)
				.addScalar("sumsewerage", StandardBasicTypes.STRING)
				.addScalar("summaintenance", StandardBasicTypes.STRING)
				.addScalar("sumoffice"  , StandardBasicTypes.STRING)
				.addScalar("sumother", StandardBasicTypes.STRING)
				.addScalar("sumrest", StandardBasicTypes.STRING)
				.addScalar("sumservicing", StandardBasicTypes.STRING)
				.addScalar("sumrent", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(AchievementSumExportModel.class));
		
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		List list = query.list();
		result.setRows(list);
		return result;
	}

}
