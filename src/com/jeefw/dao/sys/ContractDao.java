package com.jeefw.dao.sys;

import java.util.List;

import com.jeefw.model.recode.param.ExportPropertyDaoModel;
import com.jeefw.model.recode.param.ExportPropertyRespModel;
import com.jeefw.model.sys.Contract;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.param.model.BigContractModel;
import com.jeefw.model.sys.param.model.SmallContractModel;

import core.dao.Dao;
import core.support.JqGridPageView;

/**
 * 合同主体信息的数据持久层的接口
 * @JC
 */
public interface ContractDao extends Dao<Contract> {

    String getMaxNumber(String number);

    JqGridPageView<Contract> getContractByCondition(BigContractModel model);

    void deleteEntity(String id, SysUser sysUser);

    JqGridPageView<BigContractModel> getContractWithInfoById(BigContractModel model);

    JqGridPageView<SmallContractModel> getContractByAudit(BigContractModel model);

	List<ExportPropertyDaoModel> getExportInfo(ExportPropertyRespModel model);

	List<com.jeefw.model.recode.param.ExportParkingDaoModel> getExportCarInfo(
			ExportPropertyRespModel model);
}
