package com.jeefw.model.sys.param.model;

import com.jeefw.model.recode.param.BaseModel;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 合同主体信息请求类
 *
 * @JC
 */
public class ContractModel<T, G> extends BaseModel<T, G> implements Serializable {
    private static final long serialVersionUID = -4710008830886392962L;
    private String id;
    private String sysnumber;
    private Date startdate;
    private Date enddate;
    private int contype;
    private BigDecimal totalamount;
    private String partacode;
    private String partaname;
    private String partaaddress;
    private String partalegalperson;
    private String partancontact;
    private String partaaccount;
    private String partaaccountname;
    private String bankname;
    private String partbcode;
    private String partataxnumber;
    private String partbname;
    private String partbaddress;
    private String partblegalperson;
    private String partbncontact;
    private String partbtaxnumber;
    private String subsidiary;
    private String partbaccount;
    private String partbaccountname;
    private String partbbankname;
    private int auditstate;
    private String dealusers;
    private String createuser;
    private Date createtime;
    private String updateuser;
    private Date updatetime;
    private String deleteuser;
    private Date deletetime;
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

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

    public String getPartbncontact() {
        return partbncontact;
    }

    public void setPartbncontact(String partbncontact) {
        this.partbncontact = partbncontact;
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

    public String getPartbaccount() {
        return partbaccount;
    }

    public void setPartbaccount(String partbaccount) {
        this.partbaccount = partbaccount;
    }

    public String getPartbaccountname() {
        return partbaccountname;
    }

    public void setPartbaccountname(String partbaccountname) {
        this.partbaccountname = partbaccountname;
    }

    public String getPartbbankname() {
        return partbbankname;
    }

    public void setPartbbankname(String partbbankname) {
        this.partbbankname = partbbankname;
    }
}