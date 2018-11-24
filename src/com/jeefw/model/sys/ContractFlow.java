package com.jeefw.model.sys;

import com.jeefw.model.sys.param.ContractFiletParameter;
import com.jeefw.model.sys.param.ContractFlowParameter;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * 合同流转信息实体类
 *
 * @JC
 */
@Entity
@Table(name = "t_contract_flow")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag"})
@Indexed
public class ContractFlow extends ContractFlowParameter {

    private static final long serialVersionUID = -5203647994042621382L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "code", length = 32, nullable = false, unique = true)
    private String code;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "decision")
    private int decision;

    @Column(name = "dealuser")
    private String dealuser;

    @Column(name = "dealname")
    private String dealname;

    @Column(name = "nexthandler")
    private String nexthandler;

    @Column(name = "handlertime")
    private Date handlertime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getHandlertime() {
        return handlertime;
    }

    public void setHandlertime(Date handlertime) {
        this.handlertime = handlertime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractFlow that = (ContractFlow) o;
        return decision == that.decision &&
                Objects.equals(code, that.code) &&
                Objects.equals(contractcode, that.contractcode) &&
                Objects.equals(opinion, that.opinion) &&
                Objects.equals(dealuser, that.dealuser) &&
                Objects.equals(dealname, that.dealname) &&
                Objects.equals(nexthandler, that.nexthandler) &&
                Objects.equals(handlertime, that.handlertime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, contractcode, opinion, decision, dealuser, dealname, nexthandler, handlertime);
    }
}