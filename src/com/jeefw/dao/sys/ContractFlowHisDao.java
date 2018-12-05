package com.jeefw.dao.sys;

import com.jeefw.model.sys.ContractFlowHis;
import com.jeefw.model.sys.param.model.ContractFlowHisModel;
import core.dao.Dao;
import core.support.JqGridPageView;

/**
 * 合同流转历史信息的数据持久层的接口
 * @JC
 */
public interface ContractFlowHisDao extends Dao<ContractFlowHis> {

    public JqGridPageView<ContractFlowHisModel> getAuditRecords(String contractcode);

    ContractFlowHis getBackDealuser(String contractcode,String role);

}
