package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractFileDao;
import com.jeefw.model.sys.Contract;
import com.jeefw.model.sys.ContractFile;
import com.jeefw.model.sys.param.model.BigContractModel;
import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.StringUnit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同附件信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractFileDaoImpl extends BaseDao<ContractFile> implements ContractFileDao {

	public ContractFileDaoImpl() {
		super(ContractFile.class);
	}

	@Override
	public JqGridPageView<ContractFile> getContractFileByCondition(BigContractModel model) {
		JqGridPageView<ContractFile> result = new JqGridPageView<ContractFile>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		//等于查询
		if(model.getEqparam() != null){
			BigContractModel eqmodel = (BigContractModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getFiletype())){
				sb.append(" and filetype = '"+eqmodel.getFiletype()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and contractcode = '"+eqmodel.getId()+"' ");
			}
		}


		Query query = session.createQuery("from ContractFile "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<ContractFile> list = query.list();

		Object cout = session.createSQLQuery(
				"select count(1) from t_contract_file " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setTotalNumber((int)count);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public List<ContractFile> getContractFileByContractId(String id) {
		List<ContractFile> result = new ArrayList<ContractFile>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" from ContractFile  where 1 = 1  and contractcode = '"+id+"' and filetype='3'");
		Query query = session.createQuery(sb.toString());
		List<ContractFile> list = query.list();
		return list;
	}

	@Override
	public List<ContractFile> getContractFileByContractIdAndType(String id, String type) {
		List<ContractFile> result = new ArrayList<ContractFile>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" from ContractFile  where 1 = 1  and contractcode = '"+id+"' and filetype='"+type+"'");
		Query query = session.createQuery(sb.toString());
		List<ContractFile> list = query.list();
		return list;
	}
}
