package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractParkingDao;
import com.jeefw.dao.sys.ContractPropertyDao;
import com.jeefw.model.sys.ContractParking;
import com.jeefw.model.sys.ContractProperty;
import com.jeefw.service.sys.ContractParkingService;
import com.jeefw.service.sys.ContractPropertyService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 合同停车信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractParkingServiceImpl extends BaseService<ContractParking> implements ContractParkingService {
	private ContractParkingDao contractParkingDao;

	@Resource
	public void setContractParkingDao(ContractParkingDao contractParkingDao) {
		this.contractParkingDao = contractParkingDao;
		this.dao = contractParkingDao;
	}
}
