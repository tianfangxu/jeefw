package com.jeefw.model.recode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.jeefw.model.recode.param.MasterModel;

import core.support.ExtJSBaseParameter;

@MappedSuperclass
public class BaseEntity extends ExtJSBaseParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//标识
	private String id;

	@Column(name = "createuser")
	private String createuser;//创建人
	@Column(name = "createtime")
	private String createtime;//创建时间
	@Column(name = "updateuser")
	private String updateuser;//修改人
	@Column(name = "updatetime")
	private String updatetime;//修改时间
	@Column(name = "deleteuser")
	private String deleteuser;//删除人
	@Column(name = "deletetime")
	private String deletetime;//删除时间
	@Column(name = "deleteflg")
	private String deleteflg;//删除标记
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getDeleteuser() {
		return deleteuser;
	}
	public void setDeleteuser(String deleteuser) {
		this.deleteuser = deleteuser;
	}
	public String getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(String deletetime) {
		this.deletetime = deletetime;
	}
	public String getDeleteflg() {
		return deleteflg;
	}
	public void setDeleteflg(String deleteflg) {
		this.deleteflg = deleteflg;
	}
	@Transient
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
