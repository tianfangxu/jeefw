package com.jeefw.model.sys;

import com.jeefw.model.sys.param.CompactParameter;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 合同实体类
 *
 * @JC
 */
@Entity
@Table(name = "m_compact")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag"})
@Indexed
public class Compact extends CompactParameter {

    private static final long serialVersionUID = -5521008981055226110L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Field(index = Index.YES, analyzer = @Analyzer(impl = SmartChineseAnalyzer.class), store = Store.YES)
    @Column(name = "number")
    private String number;
    @Column(name="type")
    private int type;
    @Column(name="starttime")
    private String starttime;
    @Column(name="endtime")
    private String endtime;
    @Column(name="price")
    private String price;
    @Column(name="time")
    private String time;
    @Field(index = Index.YES, analyzer = @Analyzer(impl = SmartChineseAnalyzer.class), store = Store.YES)
    @Column(name="buildid")
    private String buildid;
    @Column(name="propertyid")
    private String propertyid;
    @Column(name="firstpartyid")
    private String firstpartyid;
    @Column(name="customerid")
    private String customerid;
    @Column(name="supplementaryterms")
    private String supplementaryterms;
    @Column(name="buildarera")
    private String buildarera;
    @Column(name="tenantarea")
    private String tenantarea;
    @Column(name="managefee")
    private String managefee;
    @Column(name="payway")
    private String payway;
    @Column(name="prodeposit")
    private String prodeposit;
    @Column(name="eleprice")
    private String eleprice;
    @Column(name="parkowner")
    private String parkowner;
    @Column(name="downparkprice")
    private String downparkprice;
    @Column(name="upparkprice")
    private String upparkprice;
    @Column(name="downparknum")
    private int downparknum;
    @Column(name="upparknum")
    private int upparknum;
    @Column(name="rentalfee")
    private String rentalfee;
    @Column(name="parkpayway")
    private String parkpayway;
    @Column(name="licensefee")
    private String licensefee;
    @Column(name="permitfee")
    private String permitfee;
    @Column(name="auditstate")
    private int auditstate;
    @Column(name="pdfurl")
    private String pdfurl;
    @Column(name="wordurl")
    private String wordurl;
    @Column(name="addtime")
    private String addtime;
    @Column(name="adduser")
    private String adduser;
    @Column(name="updatetime")
    private String updatetime;
    @Column(name="updateuser")
    private String updateuser;
    @Column(name="deltime")
    private String deltime;
    @Column(name="deluser")
    private String deluser;
    @Column(name="delflag")
    private String delflag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getFirstpartyid() {
        return firstpartyid;
    }

    public void setFirstpartyid(String firstpartyid) {
        this.firstpartyid = firstpartyid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getSupplementaryterms() {
        return supplementaryterms;
    }

    public void setSupplementaryterms(String supplementaryterms) {
        this.supplementaryterms = supplementaryterms;
    }

    public String getBuildarera() {
        return buildarera;
    }

    public void setBuildarera(String buildarera) {
        this.buildarera = buildarera;
    }

    public String getTenantarea() {
        return tenantarea;
    }

    public void setTenantarea(String tenantarea) {
        this.tenantarea = tenantarea;
    }

    public String getManagefee() {
        return managefee;
    }

    public void setManagefee(String managefee) {
        this.managefee = managefee;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getProdeposit() {
        return prodeposit;
    }

    public void setProdeposit(String prodeposit) {
        this.prodeposit = prodeposit;
    }

    public String getEleprice() {
        return eleprice;
    }

    public void setEleprice(String eleprice) {
        this.eleprice = eleprice;
    }

    public String getParkowner() {
        return parkowner;
    }

    public void setParkowner(String parkowner) {
        this.parkowner = parkowner;
    }

    public String getDownparkprice() {
        return downparkprice;
    }

    public void setDownparkprice(String downparkprice) {
        this.downparkprice = downparkprice;
    }

    public String getUpparkprice() {
        return upparkprice;
    }

    public void setUpparkprice(String upparkprice) {
        this.upparkprice = upparkprice;
    }

    public int getDownparknum() {
        return downparknum;
    }

    public void setDownparknum(int downparknum) {
        this.downparknum = downparknum;
    }

    public int getUpparknum() {
        return upparknum;
    }

    public void setUpparknum(int upparknum) {
        this.upparknum = upparknum;
    }

    public String getRentalfee() {
        return rentalfee;
    }

    public void setRentalfee(String rentalfee) {
        this.rentalfee = rentalfee;
    }

    public String getParkpayway() {
        return parkpayway;
    }

    public void setParkpayway(String parkpayway) {
        this.parkpayway = parkpayway;
    }

    public String getLicensefee() {
        return licensefee;
    }

    public void setLicensefee(String licensefee) {
        this.licensefee = licensefee;
    }

    public String getPermitfee() {
        return permitfee;
    }

    public void setPermitfee(String permitfee) {
        this.permitfee = permitfee;
    }

    public int getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(int auditstate) {
        this.auditstate = auditstate;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public String getWordurl() {
        return wordurl;
    }

    public void setWordurl(String wordurl) {
        this.wordurl = wordurl;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getDeltime() {
        return deltime;
    }

    public void setDeltime(String deltime) {
        this.deltime = deltime;
    }

    public String getDeluser() {
        return deluser;
    }

    public void setDeluser(String deluser) {
        this.deluser = deluser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compact compact = (Compact) o;
        return type == compact.type &&
                downparknum == compact.downparknum &&
                upparknum == compact.upparknum &&
                auditstate == compact.auditstate &&
                Objects.equals(id, compact.id) &&
                Objects.equals(number, compact.number) &&
                Objects.equals(starttime, compact.starttime) &&
                Objects.equals(endtime, compact.endtime) &&
                Objects.equals(price, compact.price) &&
                Objects.equals(time, compact.time) &&
                Objects.equals(buildid, compact.buildid) &&
                Objects.equals(propertyid, compact.propertyid) &&
                Objects.equals(firstpartyid, compact.firstpartyid) &&
                Objects.equals(customerid, compact.customerid) &&
                Objects.equals(supplementaryterms, compact.supplementaryterms) &&
                Objects.equals(buildarera, compact.buildarera) &&
                Objects.equals(tenantarea, compact.tenantarea) &&
                Objects.equals(managefee, compact.managefee) &&
                Objects.equals(payway, compact.payway) &&
                Objects.equals(prodeposit, compact.prodeposit) &&
                Objects.equals(eleprice, compact.eleprice) &&
                Objects.equals(parkowner, compact.parkowner) &&
                Objects.equals(downparkprice, compact.downparkprice) &&
                Objects.equals(upparkprice, compact.upparkprice) &&
                Objects.equals(rentalfee, compact.rentalfee) &&
                Objects.equals(parkpayway, compact.parkpayway) &&
                Objects.equals(licensefee, compact.licensefee) &&
                Objects.equals(permitfee, compact.permitfee) &&
                Objects.equals(pdfurl, compact.pdfurl) &&
                Objects.equals(wordurl, compact.wordurl) &&
                Objects.equals(addtime, compact.addtime) &&
                Objects.equals(adduser, compact.adduser) &&
                Objects.equals(updatetime, compact.updatetime) &&
                Objects.equals(updateuser, compact.updateuser) &&
                Objects.equals(deltime, compact.deltime) &&
                Objects.equals(deluser, compact.deluser) &&
                Objects.equals(delflag, compact.delflag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, type, starttime, endtime, price, time, buildid, propertyid, firstpartyid, customerid, supplementaryterms, buildarera, tenantarea, managefee, payway, prodeposit, eleprice, parkowner, downparkprice, upparkprice, downparknum, upparknum, rentalfee, parkpayway, licensefee, permitfee, auditstate, pdfurl, wordurl, addtime, adduser, updatetime, updateuser, deltime, deluser, delflag);
    }
}