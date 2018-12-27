package com.jeefw.service.recode.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.BudgetDao;
import com.jeefw.model.recode.BudgetEntity;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BudgetModel;
import com.jeefw.service.recode.BudgetService;

import core.support.JqGridPageView;
import core.util.ArithmeticUtil;
import core.util.DateUnit;
@Service
@Transactional
public class BudgetServiceImpl implements BudgetService{
	
	@Resource
	BudgetDao budgetDao;

	@SuppressWarnings("rawtypes")
	@Override
	public JqGridPageView<BudgetModel> getBudgetByCondition(BudgetModel model) {
		
		JqGridPageView<BudgetModel> view = budgetDao.getBudgetByCondition(model);
		List<BudgetModel> list = view.getRows();
		
		//计算合计预算
		for (BudgetModel bm : list) {
			//收入预算合计
//			bm.setSumincome(ArithmeticUtil.add(bm.getProperty(),bm.getFixedparking(),bm.getTempparking(),bm.getService(),bm.getAdvertising(),
//					bm.getWarehouse(),bm.getRental(),bm.getElectricin()));
			bm.setSumincome(ArithmeticUtil.add(bm.getProperty(),bm.getFixedparking(),bm.getTempparking(),bm.getAdvertising(),
					bm.getRent(),bm.getRest(),bm.getServicing(),bm.getElectricin(),bm.getWaterin()));
			//能源预算合计
			bm.setSumenergy(ArithmeticUtil.add(bm.getWater(),bm.getElectricout(),bm.getGas()));
			//办公预算合计
			bm.setSumoffice(ArithmeticUtil.add(bm.getStationery(),bm.getCommunication(),bm.getDrinkwater(),bm.getDoorplate(),bm.getDecorate(),
					bm.getCleanser(),bm.getAfforestation(),bm.getPpe(),bm.getTrashcleaning(),bm.getEmergencymaterial(),bm.getWallwashing(),
					bm.getAlarmservice(),bm.getPestcontrol(),bm.getSewerage(),bm.getMaintenance(),bm.getOffice()));
			//业务预算合计
//			bm.setSumbusiness(ArithmeticUtil.add(bm.getSecurity(),bm.getCleansing(),bm.getProjectout()));
			//修理预算合计
//			bm.setSumfixed(ArithmeticUtil.add(bm.getRepair(),bm.getFirefighting(),bm.getEngineering(),bm.getEquipmenttesting(),bm.getMaterial(),
//					bm.getExtinguisher(),bm.getUpkeep()));
			//其他预算合计
			bm.setSumelsed(bm.getOther());
			
			//成本预算
//			bm.setSumcost(ArithmeticUtil.add(bm.getSumbusiness(),bm.getSumelsed(),bm.getSumenergy(),bm.getSumfixed(),bm.getSumoffice()));
			bm.setSumcost(ArithmeticUtil.add(bm.getSumelsed(),bm.getSumenergy(),bm.getSumoffice()));
		}
		return view;
	}

	@Override
	public void saveBudget(BudgetModel model) {
		BudgetEntity entity = new BudgetEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getId().toString());
		budgetDao.persist(entity);
		
	}

	@Override
	public String updateBudget(BudgetModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		BudgetEntity entity = budgetDao.get(model.getId());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
			BeanUtils.copyProperties(model, entity);
			entity.setUpdatetime(DateUnit.getTime14());
			entity.setUpdateuser(model.getLoginuser().getId().toString());
			budgetDao.merge(entity);
			return "修改成功";
		}
		return "未查询到数据";
	}

	@Override
	public String deleteBudget(BudgetModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		if(model.getId().indexOf(",") != -1){
			String[] strs = model.getId().split(",");
			for (String id : strs) {
				budgetDao.deleteEntity(id,model.getLoginuser().getId().toString());
			}
		}else{
			budgetDao.deleteEntity(model.getId(),model.getLoginuser().getId().toString());
		}
		return "删除成功";
	}

}
