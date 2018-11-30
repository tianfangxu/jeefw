package com.jeefw.model.sys.param.model;

import com.jeefw.model.recode.param.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 合同物业信息请求类
 *
 * @JC
 */
public class ContractPropertyModel<T, G> extends BaseModel<T, G> implements Serializable {

    private static final long serialVersionUID = 6392715374302071230L;
    private String id;
    private String contractcode;
    private String address;
    private BigDecimal tenantarea;
    private BigDecimal buildarea;
    private BigDecimal propertyfee;
    private String paytype;
    private BigDecimal deposit;
    private BigDecimal electric;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTenantarea() {
        return tenantarea;
    }

    public void setTenantarea(BigDecimal tenantarea) {
        this.tenantarea = tenantarea;
    }

    public BigDecimal getBuildarea() {
        return buildarea;
    }

    public void setBuildarea(BigDecimal buildarea) {
        this.buildarea = buildarea;
    }

    public BigDecimal getPropertyfee() {
        return propertyfee;
    }

    public void setPropertyfee(BigDecimal propertyfee) {
        this.propertyfee = propertyfee;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getElectric() {
        return electric;
    }

    public void setElectric(BigDecimal electric) {
        this.electric = electric;
    }
}