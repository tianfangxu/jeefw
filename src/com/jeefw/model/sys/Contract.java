package com.jeefw.model.sys;

import core.support.DateSerializer;
import core.support.DateTimeSerializer;
import core.support.ExtJSBaseParameter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


/**
 * 合同主体信息实体类
 *
 * @JC
 */
@Entity
@Table(name = "t_contract")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag"})
@Indexed
public class Contract extends ExtJSBaseParameter {

    private static final long serialVersionUID = 2440131588937524651L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id", length = 32, nullable = false, unique = true)
    private String id;
    @Column(name = "sysnumber")
    private String sysnumber;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Column(name = "contype")
    private int contype;
    @Column(name = "totalamount",columnDefinition="number(10,2)")
    private BigDecimal totalamount;
    @Column(name = "partacode")
    private String partacode;
    @Column(name = "partaname")
    private String partaname;
    @Column(name = "partaaddress")
    private String partaaddress;
    @Column(name = "partalegalperson")
    private String partalegalperson;
    @Column(name = "partancontact")
    private String partancontact;
    @Column(name = "partaaccount")
    private String partaaccount;
    @Column(name = "partaaccountname")
    private String partaaccountname;
    @Column(name = "bankname")
    private String bankname;
    @Column(name = "partataxnumber")
    private String partataxnumber;
    @Column(name = "partbcode")
    private String partbcode;
    @Column(name = "partbtype")
    private String partbtype;
    @Column(name = "partbname")
    private String partbname;
    @Column(name = "partbaddress")
    private String partbaddress;
    @Column(name = "partblegalperson")
    private String partblegalperson;
    @Column(name = "partbcontact")
    private String partbcontact;
    @Column(name = "partbtaxnumber")
    private String partbtaxnumber;
    @Column(name = "subsidiary")
    private String subsidiary;
    @Column(name = "auditstate")
    private int auditstate;
    @Column(name = "dealusers")
    private String dealusers;
    @Column(name = "buildcode")
    private String buildcode;
    @Column(name = "propertycodes")
    private String propertycodes;
    @Column(name = "createuser")
    private String createuser;
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "updateuser")
    private String updateuser;
    @Column(name = "updatetime")
    private Date updatetime;
    @Column(name = "deleteuser")
    private String deleteuser;
    @Column(name = "deletetime")
    private Date deletetime;
    @Column(name = "deleteflg")
    private boolean deleteflg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysnumber() {
        return sysnumber;
    }

    public void setSysnumber(String sysnumber) {
        this.sysnumber = sysnumber;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getContype() {
        return contype;
    }

    public void setContype(int contype) {
        this.contype = contype;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public String getPartacode() {
        return partacode;
    }

    public void setPartacode(String partacode) {
        this.partacode = partacode;
    }

    public String getPartaname() {
        return partaname;
    }

    public void setPartaname(String partaname) {
        this.partaname = partaname;
    }

    public String getPartaaddress() {
        return partaaddress;
    }

    public void setPartaaddress(String partaaddress) {
        this.partaaddress = partaaddress;
    }

    public String getPartalegalperson() {
        return partalegalperson;
    }

    public void setPartalegalperson(String partalegalperson) {
        this.partalegalperson = partalegalperson;
    }

    public String getPartancontact() {
        return partancontact;
    }

    public void setPartancontact(String partancontact) {
        this.partancontact = partancontact;
    }

    public String getPartaaccount() {
        return partaaccount;
    }

    public void setPartaaccount(String partaaccount) {
        this.partaaccount = partaaccount;
    }

    public String getPartaaccountname() {
        return partaaccountname;
    }

    public void setPartaaccountname(String partaaccountname) {
        this.partaaccountname = partaaccountname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getPartbcode() {
        return partbcode;
    }

    public void setPartbcode(String partbcode) {
        this.partbcode = partbcode;
    }

    public String getPartataxnumber() {
        return partataxnumber;
    }

    public void setPartataxnumber(String partataxnumber) {
        this.partataxnumber = partataxnumber;
    }

    public String getPartbname() {
        return partbname;
    }

    public void setPartbname(String partbname) {
        this.partbname = partbname;
    }

    public String getPartbaddress() {
        return partbaddress;
    }

    public void setPartbaddress(String partbaddress) {
        this.partbaddress = partbaddress;
    }

    public String getPartblegalperson() {
        return partblegalperson;
    }

    public void setPartblegalperson(String partblegalperson) {
        this.partblegalperson = partblegalperson;
    }

    public String getPartbcontact() {
        return partbcontact;
    }

    public void setPartbcontact(String partbcontact) {
        this.partbcontact = partbcontact;
    }

    public String getPartbtaxnumber() {
        return partbtaxnumber;
    }

    public void setPartbtaxnumber(String partbtaxnumber) {
        this.partbtaxnumber = partbtaxnumber;
    }

    public String getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(String subsidiary) {
        this.subsidiary = subsidiary;
    }

    public int getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(int auditstate) {
        this.auditstate = auditstate;
    }

    public String getDealusers() {
        return dealusers;
    }

    public void setDealusers(String dealusers) {
        this.dealusers = dealusers;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDeleteuser() {
        return deleteuser;
    }

    public void setDeleteuser(String deleteuser) {
        this.deleteuser = deleteuser;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public boolean isDeleteflg() {
        return deleteflg;
    }

    public void setDeleteflg(boolean deleteflg) {
        this.deleteflg = deleteflg;
    }

    public String getPartbtype() {
        return partbtype;
    }

    public void setPartbtype(String partbtype) {
        this.partbtype = partbtype;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getPropertycodes() {
        return propertycodes;
    }

    public void setPropertycodes(String propertycodes) {
        this.propertycodes = propertycodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return contype == contract.contype &&
                auditstate == contract.auditstate &&
                deleteflg == contract.deleteflg &&
                Objects.equals(id, contract.id) &&
                Objects.equals(sysnumber, contract.sysnumber) &&
                Objects.equals(startdate, contract.startdate) &&
                Objects.equals(enddate, contract.enddate) &&
                Objects.equals(totalamount, contract.totalamount) &&
                Objects.equals(partacode, contract.partacode) &&
                Objects.equals(partaname, contract.partaname) &&
                Objects.equals(partaaddress, contract.partaaddress) &&
                Objects.equals(partalegalperson, contract.partalegalperson) &&
                Objects.equals(partancontact, contract.partancontact) &&
                Objects.equals(partaaccount, contract.partaaccount) &&
                Objects.equals(partaaccountname, contract.partaaccountname) &&
                Objects.equals(bankname, contract.bankname) &&
                Objects.equals(partataxnumber, contract.partataxnumber) &&
                Objects.equals(partbcode, contract.partbcode) &&
                Objects.equals(partbtype, contract.partbtype) &&
                Objects.equals(partbname, contract.partbname) &&
                Objects.equals(partbaddress, contract.partbaddress) &&
                Objects.equals(partblegalperson, contract.partblegalperson) &&
                Objects.equals(partbcontact, contract.partbcontact) &&
                Objects.equals(partbtaxnumber, contract.partbtaxnumber) &&
                Objects.equals(subsidiary, contract.subsidiary) &&
                Objects.equals(dealusers, contract.dealusers) &&
                Objects.equals(buildcode, contract.buildcode) &&
                Objects.equals(propertycodes, contract.propertycodes) &&
                Objects.equals(createuser, contract.createuser) &&
                Objects.equals(createtime, contract.createtime) &&
                Objects.equals(updateuser, contract.updateuser) &&
                Objects.equals(updatetime, contract.updatetime) &&
                Objects.equals(deleteuser, contract.deleteuser) &&
                Objects.equals(deletetime, contract.deletetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sysnumber, startdate, enddate, contype, totalamount, partacode, partaname, partaaddress, partalegalperson, partancontact, partaaccount, partaaccountname, bankname, partataxnumber, partbcode, partbtype, partbname, partbaddress, partblegalperson, partbcontact, partbtaxnumber, subsidiary, auditstate, dealusers, buildcode, propertycodes, createuser, createtime, updateuser, updatetime, deleteuser, deletetime, deleteflg);
    }
}