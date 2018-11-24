package com.jeefw.dao.sys;

import com.jeefw.model.sys.Contract;
import core.dao.Dao;

/**
 * 合同主体信息的数据持久层的接口
 * @JC
 */
public interface ContractDao extends Dao<Contract> {

    String getMaxNumber(String number);

}
