package com.jeefw.service.recode;

import com.jeefw.model.recode.param.EnterPropertyModel;
import com.jeefw.model.recode.param.ExportParkingDaoModel;
import com.jeefw.model.recode.param.ExportPropertyRespModel;

import core.support.JqGridPageView;

public interface ExportService {

	JqGridPageView<EnterPropertyModel> getExportInfo(ExportPropertyRespModel model) throws Exception;

	JqGridPageView<EnterPropertyModel> getExportCarInfo(ExportPropertyRespModel model)throws Exception;

}
