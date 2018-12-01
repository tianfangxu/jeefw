package com.jeefw.dao.sys;

import com.jeefw.model.sys.ContractProperty;
import core.dao.Dao;

/**
 * 合同物业信息的数据持久层的接口
 * @JC
 */
public interface ContractPropertyDao extends Dao<ContractProperty> {

    ContractProperty getContractPropertyByContractId(String contractCode);

}
