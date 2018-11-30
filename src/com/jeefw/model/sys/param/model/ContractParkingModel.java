package com.jeefw.model.sys.param.model;

import com.jeefw.model.recode.param.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 合同停车信息请求类
 *
 * @JC
 */
public class ContractParkingModel<T, G> extends BaseModel<T, G> implements Serializable {
    private static final long serialVersionUID = -1767826769091615733L;
    private  String id;
    private String contractcode;
    private String address;
    private String manager;
    private BigDecimal undergroundunit;
    private String undergroundnumber;
    private BigDecimal surfaceunit;
    private String surfacenumber;
    private BigDecimal rent;
    private int prepay;
    private BigDecimal cardfee;
    private BigDecimal reissuecardfee;

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public BigDecimal getUndergroundunit() {
        return undergroundunit;
    }

    public void setUndergroundunit(BigDecimal undergroundunit) {
        this.undergroundunit = undergroundunit;
    }

    public String getUndergroundnumber() {
        return undergroundnumber;
    }

    public void setUndergroundnumber(String undergroundnumber) {
        this.undergroundnumber = undergroundnumber;
    }

    public BigDecimal getSurfaceunit() {
        return surfaceunit;
    }

    public void setSurfaceunit(BigDecimal surfaceunit) {
        this.surfaceunit = surfaceunit;
    }

    public String getSurfacenumber() {
        return surfacenumber;
    }

    public void setSurfacenumber(String surfacenumber) {
        this.surfacenumber = surfacenumber;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public int getPrepay() {
        return prepay;
    }

    public void setPrepay(int prepay) {
        this.prepay = prepay;
    }

    public BigDecimal getCardfee() {
        return cardfee;
    }

    public void setCardfee(BigDecimal cardfee) {
        this.cardfee = cardfee;
    }

    public BigDecimal getReissuecardfee() {
        return reissuecardfee;
    }

    public void setReissuecardfee(BigDecimal reissuecardfee) {
        this.reissuecardfee = reissuecardfee;
    }
}