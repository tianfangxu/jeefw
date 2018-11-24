package com.jeefw.dao.recode;

import com.jeefw.model.recode.MasterEntity;
import com.jeefw.model.recode.param.MasterModel;

import core.dao.Dao;
import core.support.JqGridPageView;

/**
 * 甲方合同主体
 * @author Administrator
 *
 */
public interface MasterContractDao extends Dao<MasterEntity> {

	JqGridPageView<MasterEntity> getfirstpartyContractByCondition(
			MasterModel model);

	void savedata(MasterEntity entity);

	void deletefirstpartyContract(String id,String userid);

	void updatadata(MasterEntity entity);

}
