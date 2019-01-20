package com.jeefw.model.sys.param.model;

import com.jeefw.model.recode.param.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class BigContractModel <T, G> extends BaseModel<T, G> implements Serializable {
    private static final long serialVersionUID = -991757625610444736L;

    private String id;
    private String sysnumber;
    private String htsj;
    private Date startdate;
    private Date enddate;
    private String contype;
    private String totalamount;
    private String partacode;
    private String partaname;
    private String partaaddress;
    private String partalegalperson;
    private String partancontact;
    private String partaaccount;
    private String partaaccountname;
    private String bankname;
    private String partataxnumber;
    private String partbcode;
    private String partbname;
    private String partbaddress;
    private String partblegalperson;
    private String partbcontact;
    private String partbtaxnumber;
    private String subsidiary;
    private int auditstate;
    private String dealusers;

    private String contractcode;
    private String address;
    private String tenantarea;
    private String buildarea;
    private String propertyfee;
    private String paytype;
    private String deposit;
    private String electric;

    private String manager;
    private String undergroundunit;
    private String undergroundnumber;
    private String surfaceunit;
    private String surfacenumber;
    private String rent;
    private String prepay;
    private String cardfee;
    private String reissuecardfee;

    private String buildid;
    private String propertyid;
    private String partbtype;


    private String filetype;
    private String parkingid;
    private String propertyids;
    private String parkingaddress;
    private String propertyaddress;
    private String paytypecode;
    private int contypeInt;
    private String selectState;

    private String propertyText;//楼宇具体管理单元文本
    private String partbaccount;
    private String partbaccountname;
    private String partbbankname;
    private String othercontype;
    private String otherpaytype;

    private String htqx;//合同期限 1：将到期 2：已过期

    private String partbzjzl;
    private String partbzjhm;

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

    public String getHtsj() {
        return htsj;
    }

    public void setHtsj(String htsj) {
        this.htsj = htsj;
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

    public String getContype() {
        return contype;
    }

    public void setContype(String contype) {
        this.contype = contype;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
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

    public String getPartataxnumber() {
        return partataxnumber;
    }

    public void setPartataxnumber(String partataxnumber) {
        this.partataxnumber = partataxnumber;
    }

    public String getPartbcode() {
        return partbcode;
    }

    public void setPartbcode(String partbcode) {
        this.partbcode = partbcode;
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

    public String getTenantarea() {
        return tenantarea;
    }

    public void setTenantarea(String tenantarea) {
        this.tenantarea = tenantarea;
    }

    public String getBuildarea() {
        return buildarea;
    }

    public void setBuildarea(String buildarea) {
        this.buildarea = buildarea;
    }

    public String getPropertyfee() {
        return propertyfee;
    }

    public void setPropertyfee(String propertyfee) {
        this.propertyfee = propertyfee;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getUndergroundunit() {
        return undergroundunit;
    }

    public void setUndergroundunit(String undergroundunit) {
        this.undergroundunit = undergroundunit;
    }

    public String getUndergroundnumber() {
        return undergroundnumber;
    }

    public void setUndergroundnumber(String undergroundnumber) {
        this.undergroundnumber = undergroundnumber;
    }

    public String getSurfaceunit() {
        return surfaceunit;
    }

    public void setSurfaceunit(String surfaceunit) {
        this.surfaceunit = surfaceunit;
    }

    public String getSurfacenumber() {
        return surfacenumber;
    }

    public void setSurfacenumber(String surfacenumber) {
        this.surfacenumber = surfacenumber;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getPrepay() {
        return prepay;
    }

    public void setPrepay(String prepay) {
        this.prepay = prepay;
    }

    public String getCardfee() {
        return cardfee;
    }

    public void setCardfee(String cardfee) {
        this.cardfee = cardfee;
    }

    public String getReissuecardfee() {
        return reissuecardfee;
    }

    public void setReissuecardfee(String reissuecardfee) {
        this.reissuecardfee = reissuecardfee;
    }

    public String getBuildid() {
        return buildid;
    }

    public void setBuildid(String buildid) {
        this.buildid = buildid;
    }

    public String getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid;
    }

    public String getPartbtype() {
        return partbtype;
    }

    public void setPartbtype(String partbtype) {
        this.partbtype = partbtype;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }

    public String getPropertyids() {
        return propertyids;
    }

    public void setPropertyids(String propertyids) {
        this.propertyids = propertyids;
    }

    public String getParkingaddress() {
        return parkingaddress;
    }

    public void setParkingaddress(String parkingaddress) {
        this.parkingaddress = parkingaddress;
    }

    public String getPropertyaddress() {
        return propertyaddress;
    }

    public void setPropertyaddress(String propertyaddress) {
        this.propertyaddress = propertyaddress;
    }

    public String getPaytypecode() {
        return paytypecode;
    }

    public void setPaytypecode(String paytypecode) {
        this.paytypecode = paytypecode;
    }

    public int getContypeInt() {
        return contypeInt;
    }

    public void setContypeInt(int contypeInt) {
        this.contypeInt = contypeInt;
    }

    public String getSelectState() {
        return selectState;
    }

    public void setSelectState(String selectState) {
        this.selectState = selectState;
    }

    public String getPropertyText() {
        return propertyText;
    }

    public void setPropertyText(String propertyText) {
        this.propertyText = propertyText;
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

    public String getOthercontype() {
        return othercontype;
    }

    public void setOthercontype(String othercontype) {
        this.othercontype = othercontype;
    }

    public String getOtherpaytype() {
        return otherpaytype;
    }

    public void setOtherpaytype(String otherpaytype) {
        this.otherpaytype = otherpaytype;
    }

    public String getHtqx() {
        return htqx;
    }

    public void setHtqx(String htqx) {
        this.htqx = htqx;
    }

    public String getPartbzjzl() {
        return partbzjzl;
    }

    public void setPartbzjzl(String partbzjzl) {
        this.partbzjzl = partbzjzl;
    }

    public String getPartbzjhm() {
        return partbzjhm;
    }

    public void setPartbzjhm(String partbzjhm) {
        this.partbzjhm = partbzjhm;
    }
}
