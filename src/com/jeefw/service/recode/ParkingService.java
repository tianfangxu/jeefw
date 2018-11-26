package com.jeefw.service.recode;

import com.jeefw.model.recode.param.ParkingModel;

import core.support.JqGridPageView;

public interface ParkingService {

	JqGridPageView<ParkingModel> getParkingByCondition(ParkingModel model);

	String updateParking(ParkingModel model);

	void saveParking(ParkingModel model);

	String deleteParking(ParkingModel model);

}
