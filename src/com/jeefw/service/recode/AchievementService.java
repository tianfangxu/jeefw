package com.jeefw.service.recode;

import java.util.ArrayList;

import com.jeefw.model.recode.param.AchievementModel;
import com.jeefw.model.recode.param.AchievementSumExportModel;
import com.jeefw.model.recode.param.AchievementSumResponseModel;

import core.support.JqGridPageView;

public interface AchievementService {

	JqGridPageView<AchievementModel> getAchievementByCondition(
			AchievementModel model);

	String saveAchievement(AchievementModel model);

	String updateAchievement(AchievementModel model);

	String deleteAchievement(AchievementModel model);

	JqGridPageView<AchievementSumResponseModel> getsumToTable(
			AchievementModel model);

	ArrayList<String> getsumToExport(
			AchievementModel model);

}
