package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractPropertyDao;
import com.jeefw.model.sys.ContractProperty;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
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

	@Override
	public ContractProperty getContractPropertyByContractId(String contractCode) {
		Session session = this.getSession();
		Query query =  session.createQuery(" from ContractProperty where contractcode = '"+contractCode+"'");
		ContractProperty contractProperty = (ContractProperty)query.uniqueResult();
		return  contractProperty;
	}
}
