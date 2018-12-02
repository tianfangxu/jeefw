package com.jeefw.model.sys.param.model;

import org.hibernate.type.StandardBasicTypes;

import java.io.Serializable;
import java.util.Date;

public class SmallContractModel implements Serializable {
    private static final long serialVersionUID = 2123326030382714582L;

    private String id;
    private String sysnumber;
    private String partaname;
    private String partbname;
    private String partbcontact;
    private String auditstate;
    private Integer contype;
    private Date startdate;
    private Date enddate;

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

    public String getPartaname() {
        return partaname;
    }

    public void setPartaname(String partaname) {
        this.partaname = partaname;
    }

    public String getPartbname() {
        return partbname;
    }

    public void setPartbname(String partbname) {
        this.partbname = partbname;
    }

    public String getPartbcontact() {
        return partbcontact;
    }

    public void setPartbcontact(String partbcontact) {
        this.partbcontact = partbcontact;
    }

    public String getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate;
    }

    public Integer getContype() {
        return contype;
    }

    public void setContype(Integer contype) {
        this.contype = contype;
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
}
