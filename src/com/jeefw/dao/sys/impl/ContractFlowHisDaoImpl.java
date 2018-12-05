package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractFlowHisDao;
import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.ContractFlowHis;
import com.jeefw.model.sys.param.model.ContractFlowHisModel;
import com.jeefw.model.sys.param.model.SmallContractModel;
import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同流转历史信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractFlowHisDaoImpl extends BaseDao<ContractFlowHis> implements ContractFlowHisDao {

	public ContractFlowHisDaoImpl() {
		super(ContractFlowHis.class);
	}

	@Override
	public JqGridPageView<ContractFlowHisModel> getAuditRecords(String contractcode) {
		JqGridPageView<ContractFlowHisModel> result = new JqGridPageView<ContractFlowHisModel>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where contractcode = '"+contractcode+"' order by handlertime desc ");
		Query query =  session.createQuery(" from ContractFlowHis "+sb.toString());
		List<ContractFlowHis> list =  query.list();
		List<ContractFlowHisModel> contractFlowHisModels = new ArrayList<>();
		ContractFlowHisModel contractFlowHisModel = null;
		for(ContractFlowHis contractFlowHis :list){
			contractFlowHisModel = new ContractFlowHisModel();
			BeanUtils.copyPropertiesIgnoreNull(contractFlowHis,contractFlowHisModel);
			contractFlowHisModels.add(contractFlowHisModel);
		}
		result.setRows(contractFlowHisModels);
		result.setTotalNumber((int)contractFlowHisModels.size());
		return result;
	}

	@Override
	public ContractFlowHis getBackDealuser(String contractcode, String role) {
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where contractcode = '"+contractcode+"' and nexthandler = '"+role+"' order by handlertime desc ");
		Query query =  session.createQuery(" from ContractFlowHis "+sb.toString());
		List<ContractFlowHis> list =  query.list();
		return  list.get(0);
	}
}
