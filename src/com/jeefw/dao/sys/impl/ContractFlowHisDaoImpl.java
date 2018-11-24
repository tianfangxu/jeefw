package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractFlowHisDao;
import com.jeefw.model.sys.ContractFlowHis;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 合同流转历史信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractFlowHisDaoImpl extends BaseDao<ContractFlowHis> implements ContractFlowHisDao {

	public ContractFlowHisDaoImpl() {
		super(ContractFlowHis.class);
	}

}
