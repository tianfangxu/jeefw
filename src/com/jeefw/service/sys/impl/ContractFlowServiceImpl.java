package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.dao.sys.ContractFlowDao;
import com.jeefw.dao.sys.ContractFlowHisDao;
import com.jeefw.model.sys.Contract;
import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.ContractFlowHis;
import com.jeefw.model.sys.param.model.ContractFlowModel;
import com.jeefw.service.sys.ContractFlowService;
import core.service.BaseService;
import core.util.BeanUtils;
import core.util.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Date;


/**
 * 合同流转信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractFlowServiceImpl extends BaseService<ContractFlow> implements ContractFlowService {
	private ContractFlowDao contractFlowDao;

	@Resource
	private ContractDao contractDao;

	@Resource
	private ContractFlowHisDao contractFlowHisDao;


	@Resource
	public void setContractFlowDao(ContractFlowDao contractFlowDao) {
		this.contractFlowDao = contractFlowDao;
		this.dao = contractFlowDao;
	}

	@Override
	public void submitAudit(ContractFlowModel model) {
		//合同主表状态更新
		Contract contract = contractDao.get(model.getId());
		contract.setAuditstate(2);
		contract.setDealusers(model.getLoginuser().getId()+",");
		contract.setUpdateuser(model.getLoginuser().getId()+"");
		contract.setUpdatetime(new Date());
		contractDao.update(contract);
		//流程表主表记录新增
		ContractFlow contractFlow = new ContractFlow();
		contractFlow.setContractcode(model.getId());
		contractFlow.setOpinion(model.getOpinion());
		contractFlow.setDecision(1);
		contractFlow.setDealuser(model.getLoginuser().getId()+"");
		contractFlow.setDealname(model.getLoginuser().getUserName());
		contractFlow.setHandlertime(new Date());
		try {
			String flow = ConfigUtil.getConfig("flow");
			String[] flowList  =flow.split(",");
			contractFlow.setNexthandler(flowList[0]);
		}catch (FileNotFoundException e){

		}
		contractFlowDao.persist(contractFlow);
		//流程历史记录表新增
		ContractFlowHis contractFlowHis = new ContractFlowHis();
		BeanUtils.copyPropertiesIgnoreNull(contractFlow,contractFlowHis);
		contractFlowHisDao.persist(contractFlowHis);
	}
}
