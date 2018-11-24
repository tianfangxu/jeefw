package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.model.sys.Contract;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 合同主体信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractDaoImpl extends BaseDao<Contract> implements ContractDao {

	public ContractDaoImpl() {
		super(Contract.class);
	}


	@Override
	public String getMaxNumber(String number) {
		Query query = this.getSession().createSQLQuery("select sysnumber from t_contract where sysnumber like '"+number+"%' order by sysnumber desc");
		List<String> numberList =  query.list();
		if(numberList!=null&&numberList.size()>0){
			 return numberList.get(0);
		}
		return "";
	}
}
