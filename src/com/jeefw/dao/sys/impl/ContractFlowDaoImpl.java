package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractFlowDao;
import com.jeefw.model.sys.ContractFlow;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 合同流转信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractFlowDaoImpl extends BaseDao<ContractFlow> implements ContractFlowDao {

	public ContractFlowDaoImpl() {
		super(ContractFlow.class);
	}

}
