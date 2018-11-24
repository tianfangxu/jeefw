package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractPropertyDao;
import com.jeefw.model.sys.ContractProperty;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 合同物业信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractPropertyDaoImpl extends BaseDao<ContractProperty> implements ContractPropertyDao {

	public ContractPropertyDaoImpl() {
		super(ContractProperty.class);
	}

}
