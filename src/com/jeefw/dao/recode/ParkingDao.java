package com.jeefw.dao.recode;

import com.jeefw.model.recode.ParkingEntity;
import com.jeefw.model.recode.param.ParkingModel;

import core.dao.Dao;
import core.support.JqGridPageView;

public interface ParkingDao  extends Dao<ParkingEntity>{

	JqGridPageView<ParkingModel> getParkingByCondition(ParkingModel model);

	void deleteEntity(String id, String string);

}
