package com.jeefw.dao.sys;

import com.jeefw.model.sys.ContractFile;
import com.jeefw.model.sys.param.model.BigContractModel;
import core.dao.Dao;
import core.support.JqGridPageView;

import java.util.List;

/**
 * 合同附近信息的数据持久层的接口
 * @JC
 */
public interface ContractFileDao extends Dao<ContractFile> {

    JqGridPageView<ContractFile> getContractFileByCondition(BigContractModel model);

    List<ContractFile> getContractFileByContractId(String id);

    List<ContractFile> getContractFileByContractIdAndType(String id,String type);

}
