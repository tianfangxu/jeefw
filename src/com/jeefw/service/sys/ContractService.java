package com.jeefw.service.sys;

import com.jeefw.model.sys.Contract;
import core.service.Service;

/**
 * 合同主体信息的业务逻辑层的接口
 * @JC
 */
public interface ContractService extends Service<Contract> {

    String createNewContractNumber(int type);

}
