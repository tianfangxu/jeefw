package com.jeefw.model.sys;

import com.jeefw.model.sys.param.ContractFiletParameter;
import com.jeefw.model.sys.param.ContractParameter;
import core.support.DateSerializer;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


/**
 * 合同附件信息实体类
 *
 * @JC
 */
@Entity
@Table(name = "t_contract_file")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag"})
@Indexed
public class ContractFile extends ContractFiletParameter {

    private static final long serialVersionUID = 1090014693663977139L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "code", length = 32, nullable = false, unique = true)
    private String code;
    @Column(name = "contractcode")
    private String contractcode;
    @Column(name = "filetype")
    private int filetype;
    @Column(name = "filerurl")
    private String filerurl;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractFile that = (ContractFile) o;
        return deleteflg == that.deleteflg &&
                Objects.equals(code, that.code) &&
                Objects.equals(contractcode, that.contractcode) &&
                Objects.equals(filetype, that.filetype) &&
                Objects.equals(filerurl, that.filerurl) &&
                Objects.equals(createuser, that.createuser) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updateuser, that.updateuser) &&
                Objects.equals(updatetime, that.updatetime) &&
                Objects.equals(deleteuser, that.deleteuser) &&
                Objects.equals(deletetime, that.deletetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, contractcode, filetype, filerurl, createuser, createtime, updateuser, updatetime, deleteuser, deletetime, deleteflg);
    }
}