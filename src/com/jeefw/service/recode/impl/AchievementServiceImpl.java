package com.jeefw.service.recode.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.AchievementDao;
import com.jeefw.dao.recode.BuildDao;
import com.jeefw.model.recode.AchievementEntity;
import com.jeefw.model.recode.BudgetEntity;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.AchievementModel;
import com.jeefw.model.recode.param.AchievementSumExportModel;
import com.jeefw.model.recode.param.AchievementSumResponseModel;
import com.jeefw.model.recode.param.BudgetModel;
import com.jeefw.model.recode.param.BuildModel;
import com.jeefw.service.recode.AchievementService;

import core.support.JqGridPageView;
import core.util.ArithmeticUtil;
import core.util.DateUnit;

@Service
@Transactional
public class AchievementServiceImpl<E> implements AchievementService {
	
	@Resource
	AchievementDao achievementDao;
	
	@Resource
	BuildDao buildDao;

	@SuppressWarnings("rawtypes")
	@Override
	public JqGridPageView<AchievementModel> getAchievementByCondition(
			AchievementModel model) {
		BuildModel cmodel = new BuildModel();
		cmodel.setLoginuser(model.getLoginuser());
		cmodel.setPage("1");
		cmodel.setRows("1000");
		List<BuildEntity> rows = buildDao.getBuildByCondition(cmodel, null).getRows();
		String ids = "";
		if(rows != null && rows.size() != 0){
			for (BuildEntity buildEntity : rows) {
				ids += "'"+buildEntity.getId()+"',";
			}
		}
		ids = ids.substring(0, ids.length()-1);
		model.setBuilds(ids);
		JqGridPageView<AchievementModel> view = achievementDao.getBudgetByCondition(model);
		if(view == null){
			return null;
		}
		List<AchievementModel> list = view.getRows();
		
		//计算合计预算
		for (AchievementModel bm : list) {
			//收入预算合计
//			bm.setSumincome(ArithmeticUtil.add(bm.getProperty(),bm.getFixedparking(),bm.getTempparking(),bm.getService(),bm.getAdvertising(),
//					bm.getWarehouse(),bm.getRental(),bm.getElectricin()));
			bm.setSumincome(ArithmeticUtil.add(bm.getProperty(),bm.getFixedparking(),bm.getTempparking(),bm.getAdvertising(),
					bm.getRent(),bm.getRest(),bm.getServicing(),bm.getElectricin(),bm.getWaterin(),bm.getService(),bm.getWarehouse()
					,bm.getRental()));
			//能源预算合计
			bm.setSumenergy(ArithmeticUtil.add(bm.getWater(),bm.getElectricout(),bm.getGas()));
			//办公预算合计
			bm.setSumoffice(ArithmeticUtil.add(bm.getStationery(),bm.getCommunication(),bm.getDrinkwater(),bm.getDoorplate(),bm.getDecorate(),
					bm.getCleanser(),bm.getAfforestation(),bm.getPpe(),bm.getTrashcleaning(),bm.getEmergencymaterial(),bm.getWallwashing(),
					bm.getAlarmservice(),bm.getPestcontrol(),bm.getSewerage(),bm.getMaintenance(),bm.getOffice()));
			//业务预算合计
			bm.setSumbusiness(ArithmeticUtil.add(bm.getSecurity(),bm.getCleansing(),bm.getProjectout()));
			//修理预算合计
			bm.setSumfixed(ArithmeticUtil.add(bm.getRepair(),bm.getFirefighting(),bm.getEngineering(),bm.getEquipmenttesting(),bm.getMaterial(),
					bm.getExtinguisher(),bm.getUpkeep()));
			//其他预算合计
			bm.setSumelsed(bm.getOther());
			
			//成本预算
			bm.setSumcost(ArithmeticUtil.add(bm.getSumbusiness(),bm.getSumelsed(),bm.getSumenergy(),bm.getSumfixed(),bm.getSumoffice()));
//			bm.setSumcost(ArithmeticUtil.add(bm.getSumelsed(),bm.getSumenergy(),bm.getSumoffice()));
		}
		return view;
	}

	@Override
	public void saveAchievement(AchievementModel model) {
		AchievementEntity entity = new AchievementEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getId().toString());
		achievementDao.persist(entity);
	}

	@Override
	public String updateAchievement(AchievementModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		AchievementEntity entity = achievementDao.get(model.getId());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
			BeanUtils.copyProperties(model, entity);
			entity.setUpdatetime(DateUnit.getTime14());
			entity.setUpdateuser(model.getLoginuser().getId().toString());
			achievementDao.merge(entity);
			return "修改成功";
		}
		return "未查询到数据";
	}

	@Override
	public String deleteAchievement(AchievementModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		if(model.getId().indexOf(",") != -1){
			String[] strs = model.getId().split(",");
			for (String id : strs) {
				achievementDao.deleteEntity(id,model.getLoginuser().getId().toString());
			}
		}else{
			achievementDao.deleteEntity(model.getId(),model.getLoginuser().getId().toString());
		}
		return "删除成功";
	}

	@Override
	public JqGridPageView<AchievementSumResponseModel> getsumToTable(
			AchievementModel model) {
		BuildModel cmodel = new BuildModel();
		cmodel.setLoginuser(model.getLoginuser());
		cmodel.setPage("1");
		cmodel.setRows("1000");
		List<BuildEntity> rows = buildDao.getBuildByCondition(cmodel, null).getRows();
		String ids = "";
		if(rows != null && rows.size() != 0){
			for (BuildEntity buildEntity : rows) {
				ids += "'"+buildEntity.getId()+"',";
			}
		}
		ids = ids.substring(0, ids.length()-1);
		model.setBuilds(ids);
		return achievementDao.sumToTable(model);
		
	}

	@Override
	public ArrayList<String> getsumToExport(
			AchievementModel model) {
		ArrayList<String> arrayList = null;
		JqGridPageView<AchievementSumExportModel> sumToExport = achievementDao.sumToExport(model);
		List<AchievementSumExportModel> list = sumToExport.getRows();
		if(list != null && list.size() != 0){
			AchievementSumExportModel exportModel = list.get(0);
			arrayList = new ArrayList<String>();
			setExcleDate(exportModel, arrayList);
		}
		return arrayList;
	}
	
	//报表数据
	public ArrayList<String> setExcleDate(AchievementSumExportModel model,ArrayList<String> list){
		//应完成度
		String divide = ArithmeticUtil.divide(model.getMonth(),"12");
		
		list.add(model.getRent() );
		list.add(model.getSumrent() );
		list.add(divide);
		try {
			list.add(ArithmeticUtil.divide(model.getSumrent(), ArithmeticUtil.multiply(model.getRent(), divide)));
			list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumrent(), ArithmeticUtil.multiply(model.getRent(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		
		
		list.add(model.getProperty());
		list.add(model.getSumproperty());
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumproperty(), ArithmeticUtil.multiply(model.getProperty(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumproperty(), ArithmeticUtil.multiply(model.getProperty(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		
		list.add(model.getFixedparking() );
		list.add(model.getSumfixedparking() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumfixedparking(), ArithmeticUtil.multiply(model.getFixedparking(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumfixedparking(), ArithmeticUtil.multiply(model.getFixedparking(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getTempparking() );
		list.add(model.getSumtempparking() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumtempparking(), ArithmeticUtil.multiply(model.getTempparking(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumtempparking(), ArithmeticUtil.multiply(model.getTempparking(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getAdvertising() );
		list.add(model.getSumadvertising() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumadvertising(), ArithmeticUtil.multiply(model.getAdvertising(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumadvertising(), ArithmeticUtil.multiply(model.getAdvertising(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getRest() );
		list.add(model.getSumrest() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumrest(), ArithmeticUtil.multiply(model.getRest(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumrest(), ArithmeticUtil.multiply(model.getRest(), divide)), divide));
	} catch (Exception e) {
		list.add("0");
		list.add("0");
	}
		list.add(model.getServicing() );
		list.add(model.getSumservicing() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumservicing(), ArithmeticUtil.multiply(model.getServicing(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumservicing(), ArithmeticUtil.multiply(model.getServicing(), divide)), divide));
	} catch (Exception e) {
		list.add("0");
		list.add("0");
	}
		list.add(model.getElectricin() );
		list.add(model.getSumelectricin() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumelectricin(), ArithmeticUtil.multiply(model.getElectricin(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumelectricin(), ArithmeticUtil.multiply(model.getElectricin(), divide)), divide));
	} catch (Exception e) {
		list.add("0");
		list.add("0");
	}
		list.add(model.getWaterin() );
		list.add(model.getSumwaterin() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumwaterin(), ArithmeticUtil.multiply(model.getWaterin(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumwaterin(), ArithmeticUtil.multiply(model.getWaterin(), divide)), divide));
	} catch (Exception e) {
		list.add("0");
		list.add("0");
	}
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		
		list.add("");
		
		list.add(model.getElectricout() );
		list.add(model.getSumelectricout() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumelectricout(), ArithmeticUtil.multiply(model.getElectricout(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumelectricout(), ArithmeticUtil.multiply(model.getElectricout(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getWater() );
		list.add(model.getSumwater() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumwater(), ArithmeticUtil.multiply(model.getWater(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumwater(), ArithmeticUtil.multiply(model.getWater(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getGas() );
		list.add(model.getSumgas() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumgas(), ArithmeticUtil.multiply(model.getGas(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumgas(), ArithmeticUtil.multiply(model.getGas(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		
		list.add(model.getStationery() );
		list.add(model.getSumstationery() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumstationery(), ArithmeticUtil.multiply(model.getStationery(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumstationery(), ArithmeticUtil.multiply(model.getStationery(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getCommunication() );
		list.add(model.getSumcommunication() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumcommunication(), ArithmeticUtil.multiply(model.getCommunication(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumcommunication(), ArithmeticUtil.multiply(model.getCommunication(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getDrinkwater() );
		list.add(model.getSumdrinkwater() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumdrinkwater(), ArithmeticUtil.multiply(model.getDrinkwater(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumdrinkwater(), ArithmeticUtil.multiply(model.getDrinkwater(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getDoorplate() );
		list.add(model.getSumdoorplate() );
		list.add(divide);
		try {
		list.add(ArithmeticUtil.divide(model.getSumdoorplate(), ArithmeticUtil.multiply(model.getDoorplate(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumdoorplate(), ArithmeticUtil.multiply(model.getDoorplate(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getDecorate() );
		list.add(model.getSumdecorate() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumdecorate(), ArithmeticUtil.multiply(model.getDecorate(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumdecorate(), ArithmeticUtil.multiply(model.getDecorate(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getCleanser() );
		list.add(model.getSumcleanser() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumcleanser(), ArithmeticUtil.multiply(model.getCleanser(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumcleanser(), ArithmeticUtil.multiply(model.getCleanser(), divide)), divide));
		} catch (Exception e) {
		list.add("0");
		list.add("0");
		}
		list.add(model.getAfforestation() );
		list.add(model.getSumafforestation() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumafforestation(), ArithmeticUtil.multiply(model.getAfforestation(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumafforestation(), ArithmeticUtil.multiply(model.getAfforestation(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getPpe() );
		list.add(model.getSumppe() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumppe(), ArithmeticUtil.multiply(model.getPpe(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumppe(), ArithmeticUtil.multiply(model.getPpe(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getTrashcleaning() );
		list.add(model.getSumtrashcleaning() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumtrashcleaning(), ArithmeticUtil.multiply(model.getTrashcleaning(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumtrashcleaning(), ArithmeticUtil.multiply(model.getTrashcleaning(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getEmergencymaterial() );
		list.add(model.getSumemergencymaterial() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumemergencymaterial(), ArithmeticUtil.multiply(model.getEmergencymaterial(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumemergencymaterial(), ArithmeticUtil.multiply(model.getEmergencymaterial(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getWallwashing() );
		list.add(model.getSumwallwashing() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumwallwashing(), ArithmeticUtil.multiply(model.getWallwashing(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumwallwashing(), ArithmeticUtil.multiply(model.getWallwashing(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getAlarmservice() );
		list.add(model.getSumalarmservice() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumalarmservice(), ArithmeticUtil.multiply(model.getAlarmservice(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumalarmservice(), ArithmeticUtil.multiply(model.getAlarmservice(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getPestcontrol() );
		list.add(model.getSumpestcontrol() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumpestcontrol(), ArithmeticUtil.multiply(model.getPestcontrol(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumpestcontrol(), ArithmeticUtil.multiply(model.getPestcontrol(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getSewerage() );
		list.add(model.getSumsewerage() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumsewerage(), ArithmeticUtil.multiply(model.getSewerage(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumsewerage(), ArithmeticUtil.multiply(model.getSewerage(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getMaintenance() );
		list.add(model.getSummaintenance() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSummaintenance(), ArithmeticUtil.multiply(model.getMaintenance(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSummaintenance(), ArithmeticUtil.multiply(model.getMaintenance(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add(model.getOffice() );
		list.add(model.getSumoffice() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumoffice(), ArithmeticUtil.multiply(model.getOffice(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumoffice(), ArithmeticUtil.multiply(model.getOffice(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		
		list.add(model.getOther() );
		list.add(model.getSumother() );
		list.add(divide);try {
		list.add(ArithmeticUtil.divide(model.getSumother(), ArithmeticUtil.multiply(model.getOther(), divide)));
		list.add(ArithmeticUtil.subtract(ArithmeticUtil.divide(model.getSumother(), ArithmeticUtil.multiply(model.getOther(), divide)), divide));
		} catch (Exception e) {
			list.add("0");
			list.add("0");
		}
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		
		return list;
	}

}
