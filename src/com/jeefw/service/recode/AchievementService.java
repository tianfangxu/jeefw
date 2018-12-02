package com.jeefw.service.recode;

import com.jeefw.model.recode.param.AchievementModel;

import core.support.JqGridPageView;

public interface AchievementService {

	JqGridPageView<AchievementModel> getAchievementByCondition(
			AchievementModel model);

	void saveAchievement(AchievementModel model);

	String updateAchievement(AchievementModel model);

	String deleteAchievement(AchievementModel model);

}
