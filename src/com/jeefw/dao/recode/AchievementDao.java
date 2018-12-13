package com.jeefw.dao.recode;

import com.jeefw.model.recode.AchievementEntity;
import com.jeefw.model.recode.param.AchievementModel;
import com.jeefw.model.recode.param.AchievementSumExportModel;
import com.jeefw.model.recode.param.AchievementSumResponseModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface AchievementDao extends Dao<AchievementEntity> {

	JqGridPageView<AchievementModel> getBudgetByCondition(AchievementModel model);

	void deleteEntity(String id, String string);
	
	JqGridPageView<AchievementSumResponseModel> sumToTable(AchievementModel model);
	
	JqGridPageView<AchievementSumExportModel> sumToExport(AchievementModel model);
	
}
