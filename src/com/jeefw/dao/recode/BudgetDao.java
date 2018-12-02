package com.jeefw.dao.recode;

import com.jeefw.model.recode.BudgetEntity;
import com.jeefw.model.recode.param.BudgetModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface BudgetDao  extends Dao<BudgetEntity>{

	JqGridPageView<BudgetModel> getBudgetByCondition(BudgetModel model);

	void deleteEntity(String id, String string);

}
