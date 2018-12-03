package com.jeefw.service.sys;

import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.param.model.ContractFlowHisModel;
import com.jeefw.model.sys.param.model.ContractFlowModel;
import core.service.Service;
import core.support.JqGridPageView;

/**
 * 合同流转信息的业务逻辑层的接口
 *
 * @JC
 */
public interface ContractFlowService extends Service<ContractFlow> {

    void submitAudit(ContractFlowModel model);

    JqGridPageView<ContractFlowHisModel> getAuditRecords(ContractFlowModel model);

}
