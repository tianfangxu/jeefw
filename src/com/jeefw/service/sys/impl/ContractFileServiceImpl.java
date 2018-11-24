package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractFileDao;
import com.jeefw.model.sys.ContractFile;
import com.jeefw.service.sys.ContractFileService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 合同附件信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractFileServiceImpl extends BaseService<ContractFile> implements ContractFileService {
	private ContractFileDao contractFileDao;

	@Resource
	public void setContractFileDao(ContractFileDao contractFileDao) {
		this.contractFileDao = contractFileDao;
		this.dao = contractFileDao;
	}

}
