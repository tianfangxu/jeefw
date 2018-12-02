package com.jeefw.dao.recode;

import com.jeefw.model.recode.AchievementEntity;
import com.jeefw.model.recode.param.AchievementModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface AchievementDao extends Dao<AchievementEntity> {

	JqGridPageView<AchievementModel> getBudgetByCondition(AchievementModel model);

	void deleteEntity(String id, String string);

}
