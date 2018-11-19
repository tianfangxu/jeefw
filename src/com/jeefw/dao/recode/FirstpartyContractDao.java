package com.jeefw.dao.recode;

import com.jeefw.model.recode.firstpartyContractEntity;
import com.jeefw.model.recode.param.firstpartyContractModel;

import core.dao.Dao;
import core.support.JqGridPageView;

/**
 * 甲方合同主体
 * @author Administrator
 *
 */
public interface FirstpartyContractDao extends Dao<firstpartyContractEntity> {

	JqGridPageView<firstpartyContractEntity> getfirstpartyContractByCondition(
			firstpartyContractModel model);

	void savedata(firstpartyContractEntity entity);

	void deletefirstpartyContract(String id);

	void updatadata(firstpartyContractEntity entity);

}
