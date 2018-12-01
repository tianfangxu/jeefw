package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractParkingDao;
import com.jeefw.model.sys.ContractParking;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * 合同停车信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractParkingDaoImpl extends BaseDao<ContractParking> implements ContractParkingDao {

	public ContractParkingDaoImpl() {
		super(ContractParking.class);
	}

	@Override
	public ContractParking getContractParkingByContractId(String contractCode) {
		Session session = this.getSession();
		Query query =  session.createQuery(" from ContractParking where contractcode = '"+contractCode+"'");
		ContractParking contractParking = (ContractParking)query.uniqueResult();
		return  contractParking;
	}


}
