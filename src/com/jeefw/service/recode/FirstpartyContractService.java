package com.jeefw.service.recode;

import com.jeefw.model.recode.firstpartyContractEntity;
import com.jeefw.model.recode.param.firstpartyContractModel;

import core.support.JqGridPageView;

public interface FirstpartyContractService {

	JqGridPageView<firstpartyContractEntity> getfirstpartyContractByCondition(
			firstpartyContractModel model);

	void savefirstpartyContract(firstpartyContractModel model);

	String updfirstpartyContract(firstpartyContractModel model);

	String deletefirstpartyContract(firstpartyContractModel model);

}
