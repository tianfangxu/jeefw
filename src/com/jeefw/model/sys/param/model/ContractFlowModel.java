package com.jeefw.model.sys.param.model;

import com.jeefw.model.recode.param.BaseModel;

import java.io.Serializable;
import java.util.Date;


/**
 * 合同流转信息请求类
 *
 * @JC
 */
public class ContractFlowModel<T, G> extends BaseModel<T, G> implements Serializable {
    private static final long serialVersionUID = 4069267618069393755L;
    private String id;
    private String contractcode;
    private String opinion;
    private int decision;
    private String dealuser;
    private String dealname;
    private String nexthandler;
    private Date handlertime;

    private String contype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public String getDealuser() {
        return dealuser;
    }

    public void setDealuser(String dealuser) {
        this.dealuser = dealuser;
    }

    public String getDealname() {
        return dealname;
    }

    public void setDealname(String dealname) {
        this.dealname = dealname;
    }

    public String getNexthandler() {
        return nexthandler;
    }

    public void setNexthandler(String nexthandler) {
        this.nexthandler = nexthandler;
    }

    public Date getHandlertime() {
        return handlertime;
    }

    public void setHandlertime(Date handlertime) {
        this.handlertime = handlertime;
    }
}