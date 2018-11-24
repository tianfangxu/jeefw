package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractFlowDao;
import com.jeefw.dao.sys.ContractFlowHisDao;
import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.ContractFlowHis;
import com.jeefw.service.sys.ContractFlowHisService;
import com.jeefw.service.sys.ContractFlowService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 合同流转历史信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractFlowHisServiceImpl extends BaseService<ContractFlowHis> implements ContractFlowHisService {
	private ContractFlowHisDao  contractFlowHisDao;

	@Resource
	public void setContractFlowDao(ContractFlowHisDao contractFlowHisDao) {
		this.contractFlowHisDao = contractFlowHisDao;
		this.dao = contractFlowHisDao;
	}

}
