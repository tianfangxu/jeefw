package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractParkingDao;
import com.jeefw.dao.sys.ContractPropertyDao;
import com.jeefw.model.sys.ContractParking;
import com.jeefw.model.sys.ContractProperty;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 合同停车信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractParkingDaoImpl extends BaseDao<ContractParking> implements ContractParkingDao {

	public ContractParkingDaoImpl() {
		super(ContractParking.class);
	}

}
