package com.jeefw.service.recode;

import com.jeefw.model.recode.MasterEntity;
import com.jeefw.model.recode.param.MasterModel;

import core.support.JqGridPageView;

public interface MasterContractService {

	JqGridPageView<MasterEntity> getfirstpartyContractByCondition(
			MasterModel model);

	void savefirstpartyContract(MasterModel model);

	String updfirstpartyContract(MasterModel model);

	String deletefirstpartyContract(MasterModel model);

}
