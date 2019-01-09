package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.dao.sys.ContractFlowDao;
import com.jeefw.dao.sys.ContractFlowHisDao;
import com.jeefw.model.sys.*;
import com.jeefw.model.sys.param.model.ContractFlowHisModel;
import com.jeefw.model.sys.param.model.ContractFlowModel;
import com.jeefw.service.sys.ContractFlowService;
import core.service.BaseService;
import core.support.JqGridPageView;
import core.util.BeanUtils;
import core.util.CommonUtil;
import core.util.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


/**
 * 合同流转信息的业务逻辑层的实现
 *
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
        contract.setDealusers("["+model.getLoginuser().getId() + "]");
        contract.setUpdateuser(model.getLoginuser().getId() + "");
        contract.setUpdatetime(new Date());
        contractDao.update(contract);
        //流程表主表记录新增
        ContractFlow contractFlow = new ContractFlow();
        contractFlow.setContractcode(model.getId());
        contractFlow.setOpinion(model.getOpinion());
        contractFlow.setDecision(3);
        contractFlow.setDealuser(model.getLoginuser().getId()+"");
        contractFlow.setDealname(model.getLoginuser().getUserName());
        contractFlow.setHandlertime(new Date());
        try {
            String flow = ConfigUtil.getConfig("flow");
            String[] flowList = flow.split(",");
            contractFlow.setNexthandler(flowList[0]);
        } catch (FileNotFoundException e) {

        }
        contractFlowDao.persist(contractFlow);
        //流程历史记录表新增
        ContractFlowHis contractFlowHis = new ContractFlowHis();
        BeanUtils.copyPropertiesIgnoreNull(contractFlow, contractFlowHis);
        contractFlowHisDao.persist(contractFlowHis);
    }

    @Override
    public void dealActiviti(ContractFlowModel model) {
        try {
            String recentDealuser = "";
            Contract contract = contractDao.get(model.getContractcode());
            contract.setDealusers(contract.getDealusers() + "["+model.getLoginuser().getId() + "]");
            contract.setUpdateuser(model.getLoginuser().getId() + "");
            contract.setUpdatetime(new Date());
            ContractFlow contractFlow = contractFlowDao.getContractFlowByContractCode(model.getContractcode());
            recentDealuser = contractFlow.getDealuser();
            contractFlow.setOpinion(model.getOpinion());
            contractFlow.setDecision(model.getDecision());
            contractFlow.setDealuser(model.getLoginuser().getId() + "");
            contractFlow.setDealname(model.getLoginuser().getUserName());
            contractFlow.setHandlertime(new Date());
            Iterator<Role> it = model.getLoginuser().getRoles().iterator();
            String role = it.next().getRoleKey();
            String flow = ConfigUtil.getConfig("flow");
            String[] flowList = flow.split(",");
            if (model.getDecision() == 1) {
                //同意
                int index = -1;
                for(int i=0;i<flowList.length;i++){
                    if(flowList[i].equals(role)){
                        index = i;
                        break;
                    }
                }
                if(index+1==flowList.length){
                    //说明已经到了最后节点，完成单子了
                    contract.setAuditstate(3);
                    contractFlow.setNexthandler("finished");
                    contractFlow.setDecision(4);
                }else{
                    contractFlow.setNexthandler(flowList[index+1]);
                }
            } else if (model.getDecision() == 2) {
                //回退
                int index = 0;
                for(int i=0;i<flowList.length;i++){
                    if(flowList[i].equals(role)){
                        index = i;
                        break;
                    }
                }
                ContractFlowHis contractFlowHis = contractFlowHisDao.getBackDealuser(model.getContractcode(),flowList[index]);
                contractFlow.setNexthandler(contractFlowHis.getDealuser());
            }
            contractDao.update(contract);
            contractFlowDao.update(contractFlow);
            //流程历史记录表新增
            ContractFlowHis contractFlowHis = new ContractFlowHis();
            BeanUtils.copyPropertiesIgnoreNull(contractFlow, contractFlowHis);
            contractFlowHisDao.persist(contractFlowHis);
        } catch (Exception e) {

        }
    }

    @Override
    public JqGridPageView<ContractFlowHisModel> getAuditRecords(ContractFlowModel model) {
        return contractFlowHisDao.getAuditRecords(model.getId());
    }
}
