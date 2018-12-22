package com.jeefw.service.recode;

import com.jeefw.model.recode.param.EnterPropertyModel;
import com.jeefw.model.recode.param.ExportPropertyRespModel;

import core.support.JqGridPageView;

public interface ExportService {

	JqGridPageView<EnterPropertyModel> getExportInfo(ExportPropertyRespModel model) throws Exception;

}
