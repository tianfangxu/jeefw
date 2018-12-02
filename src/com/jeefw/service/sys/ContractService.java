package com.jeefw.service.sys;

import com.jeefw.model.sys.Contract;
import com.jeefw.model.sys.ContractFile;
import com.jeefw.model.sys.param.model.BigContractModel;
import com.jeefw.model.sys.param.model.SmallContractModel;
import core.service.Service;
import core.support.JqGridPageView;

import java.text.ParseException;

/**
 * 合同主体信息的业务逻辑层的接口
 *
 * @JC
 */
public interface ContractService extends Service<Contract> {

    String createNewContractNumber(int type);

    void saveContract(BigContractModel model) throws ParseException;

    void updateContract(BigContractModel model) throws ParseException;

    JqGridPageView<Contract> getContractByCondition(BigContractModel model);

    JqGridPageView<ContractFile> getContractFileByCondition(BigContractModel model);

    String deleteCompact(BigContractModel model);

    JqGridPageView<BigContractModel> getContractWithInfoById(BigContractModel model);

    JqGridPageView<SmallContractModel> getContractByAudit(BigContractModel model);

}
