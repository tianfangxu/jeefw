package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractFlowDao;
import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.ContractFlowHis;
import com.jeefw.model.sys.param.model.ContractFlowHisModel;
import core.dao.BaseDao;
import core.util.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同流转信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractFlowDaoImpl extends BaseDao<ContractFlow> implements ContractFlowDao {

	public ContractFlowDaoImpl() {
		super(ContractFlow.class);
	}

	@Override
	public ContractFlow getContractFlowByContractCode(String contractCode) {
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where contractcode = '"+contractCode+"'");
		Query query =  session.createQuery(" from ContractFlow "+sb.toString());
		ContractFlow contractFlow =  (ContractFlow)query.uniqueResult();
		return contractFlow;
	}
}
