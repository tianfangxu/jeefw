package com.jeefw.model.sys;

import core.support.ExtJSBaseParameter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * 合同停车信息实体类
 *
 * @JC
 */
@Entity
@Table(name = "t_contract_parking")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag"})
@Indexed
public class ContractParking extends ExtJSBaseParameter {

    private static final long serialVersionUID = 6460181855547786321L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id", length = 32, nullable = false, unique = true)
    private  String id;
    @Column(name = "contractcode")
    private String contractcode;
    @Column(name = "address")
    private String address;
    @Column(name = "manager")
    private String manager;
    @Column(name = "undergroundunit",columnDefinition="number(10,2)")
    private BigDecimal undergroundunit;
    @Column(name = "undergroundnumber")
    private int undergroundnumber;
    @Column(name = "surfaceunit",columnDefinition="number(10,2)")
    private BigDecimal surfaceunit;
    @Column(name = "surfacenumber")
    private int surfacenumber;
    @Column(name = "rent",columnDefinition="number(10,2)")
    private BigDecimal rent;
    @Column(name = "prepay")
    private int prepay;
    @Column(name = "cardfee",columnDefinition="number(5,2)")
    private BigDecimal cardfee;
    @Column(name = "reissuecardfee",columnDefinition="number(5,2)")
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

    public int getUndergroundnumber() {
        return undergroundnumber;
    }

    public void setUndergroundnumber(int undergroundnumber) {
        this.undergroundnumber = undergroundnumber;
    }

    public BigDecimal getSurfaceunit() {
        return surfaceunit;
    }

    public void setSurfaceunit(BigDecimal surfaceunit) {
        this.surfaceunit = surfaceunit;
    }

    public int getSurfacenumber() {
        return surfacenumber;
    }

    public void setSurfacenumber(int surfacenumber) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractParking that = (ContractParking) o;
        return prepay == that.prepay &&
                Objects.equals(id, that.id) &&
                Objects.equals(contractcode, that.contractcode) &&
                Objects.equals(address, that.address) &&
                Objects.equals(manager, that.manager) &&
                Objects.equals(undergroundunit, that.undergroundunit) &&
                Objects.equals(undergroundnumber, that.undergroundnumber) &&
                Objects.equals(surfaceunit, that.surfaceunit) &&
                Objects.equals(surfacenumber, that.surfacenumber) &&
                Objects.equals(rent, that.rent) &&
                Objects.equals(cardfee, that.cardfee) &&
                Objects.equals(reissuecardfee, that.reissuecardfee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractcode, address, manager, undergroundunit, undergroundnumber, surfaceunit, surfacenumber, rent, prepay, cardfee, reissuecardfee);
    }
}