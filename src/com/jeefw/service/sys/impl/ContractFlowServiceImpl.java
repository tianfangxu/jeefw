package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractFlowDao;
import com.jeefw.model.sys.ContractFlow;
import com.jeefw.service.sys.ContractFlowService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 合同流转信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractFlowServiceImpl extends BaseService<ContractFlow> implements ContractFlowService {
	private ContractFlowDao contractFlowDao;

	@Resource
	public void setContractFlowDao(ContractFlowDao contractFlowDao) {
		this.contractFlowDao = contractFlowDao;
		this.dao = contractFlowDao;
	}

}
