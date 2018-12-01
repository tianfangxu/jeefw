package com.jeefw.dao.sys;

import com.jeefw.model.sys.ContractParking;
import core.dao.Dao;

/**
 * 合同停车信息的数据持久层的接口
 * @JC
 */
public interface ContractParkingDao extends Dao<ContractParking> {
    ContractParking getContractParkingByContractId(String contractCode);

}
