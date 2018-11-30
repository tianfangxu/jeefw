package com.jeefw.model.sys.param.model;

import com.jeefw.model.recode.param.BaseModel;

import java.io.Serializable;
import java.util.Date;


/**
 * 合同附件信息请求类
 *
 * @JC
 */
public class ContractFileModel<T, G> extends BaseModel<T, G> implements Serializable {

    private static final long serialVersionUID = 1877728850957609420L;

    private String id;
    private String contractcode;
    private int filetype;
    private String filerurl;
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

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public int getFiletype() {
        return filetype;
    }

    public void setFiletype(int filetype) {
        this.filetype = filetype;
    }

    public String getFilerurl() {
        return filerurl;
    }

    public void setFilerurl(String filerurl) {
        this.filerurl = filerurl;
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
}