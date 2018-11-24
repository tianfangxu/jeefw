package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractPropertyDao;
import com.jeefw.model.sys.ContractProperty;
import com.jeefw.service.sys.ContractPropertyService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 合同物业信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractPropertyServiceImpl extends BaseService<ContractProperty> implements ContractPropertyService {
	private ContractPropertyDao contractPropertyDao;

	@Resource
	public void setContractPropertyDao(ContractPropertyDao contractPropertyDao) {
		this.contractPropertyDao = contractPropertyDao;
		this.dao = contractPropertyDao;
	}

}
