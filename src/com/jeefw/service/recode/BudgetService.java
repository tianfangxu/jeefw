package com.jeefw.service.recode;

import com.jeefw.model.recode.BudgetEntity;
import com.jeefw.model.recode.param.BudgetModel;

import core.support.JqGridPageView;

public interface BudgetService {

	JqGridPageView<BudgetModel> getBudgetByCondition(BudgetModel model);

	void saveBudget(BudgetModel model);

	String updateBudget(BudgetModel model);

	String deleteBudget(BudgetModel model);

}
