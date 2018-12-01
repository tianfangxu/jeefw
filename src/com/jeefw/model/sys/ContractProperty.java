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
 * 合同物业信息实体类
 *
 * @JC
 */
@Entity
@Table(name = "t_contract_property")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag"})
@Indexed
public class ContractProperty  extends ExtJSBaseParameter {

    private static final long serialVersionUID = 7261688784801457243L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id", length = 32, nullable = false, unique = true)
    private String id;
    @Column(name = "contractcode")
    private String contractcode;
    @Column(name = "address")
    private String address;
    @Column(name = "tenantarea",columnDefinition="number(10,2)")
    private BigDecimal tenantarea;
    @Column(name = "buildarea",columnDefinition="number(10,2)")
    private BigDecimal buildarea;
    @Column(name = "propertyfee",columnDefinition="number(10,2)")
    private BigDecimal propertyfee;
    @Column(name = "paytype")
    private String paytype;
    @Column(name = "deposit",columnDefinition="number(10,2)")
    private BigDecimal deposit;
    @Column(name = "electric",columnDefinition="number(10,2)")
    private BigDecimal electric;
    @Column(name = "paytypecode")
    private String  paytypecode;

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

    public String getPaytypecode() {
        return paytypecode;
    }

    public void setPaytypecode(String paytypecode) {
        this.paytypecode = paytypecode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractProperty that = (ContractProperty) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(contractcode, that.contractcode) &&
                Objects.equals(address, that.address) &&
                Objects.equals(tenantarea, that.tenantarea) &&
                Objects.equals(buildarea, that.buildarea) &&
                Objects.equals(propertyfee, that.propertyfee) &&
                Objects.equals(paytype, that.paytype) &&
                Objects.equals(deposit, that.deposit) &&
                Objects.equals(electric, that.electric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractcode, address, tenantarea, buildarea, propertyfee, paytype, deposit, electric);
    }
}