package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractFileDao;
import com.jeefw.model.sys.ContractFile;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * 合同附件信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractFileDaoImpl extends BaseDao<ContractFile> implements ContractFileDao {

	public ContractFileDaoImpl() {
		super(ContractFile.class);
	}

}
