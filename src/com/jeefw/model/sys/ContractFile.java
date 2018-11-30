package com.jeefw.model.sys;

import core.support.DateTimeSerializer;
import core.support.ExtJSBaseParameter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
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
public class ContractFile extends ExtJSBaseParameter {

    private static final long serialVersionUID = 1090014693663977139L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id", length = 32, nullable = false, unique = true)
    private String id;
    @Column(name = "contractcode")
    private String contractcode;
    @Column(name = "filetype")
    private int filetype;
    @Column(name = "fileurl")
    private String fileurl;
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

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
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
        return filetype == that.filetype &&
                deleteflg == that.deleteflg &&
                Objects.equals(id, that.id) &&
                Objects.equals(contractcode, that.contractcode) &&
                Objects.equals(fileurl, that.fileurl) &&
                Objects.equals(createuser, that.createuser) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updateuser, that.updateuser) &&
                Objects.equals(updatetime, that.updatetime) &&
                Objects.equals(deleteuser, that.deleteuser) &&
                Objects.equals(deletetime, that.deletetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractcode, filetype, fileurl, createuser, createtime, updateuser, updatetime, deleteuser, deletetime, deleteflg);
    }
}