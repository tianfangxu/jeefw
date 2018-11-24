package com.jeefw.service.sys;

import com.jeefw.model.sys.Compact;
import core.service.Service;

/**
 * 合同的业务逻辑层的接口
 * @JC
 */
public interface CompactService extends Service<Compact> {

    String createNewContractNumber(int type);

}
