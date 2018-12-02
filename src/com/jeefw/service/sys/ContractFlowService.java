package com.jeefw.service.sys;

import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.param.model.ContractFlowModel;
import core.service.Service;

/**
 * 合同流转信息的业务逻辑层的接口
 *
 * @JC
 */
public interface ContractFlowService extends Service<ContractFlow> {

    void submitAudit(ContractFlowModel model);

}
