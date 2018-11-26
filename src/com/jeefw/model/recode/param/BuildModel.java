package com.jeefw.model.recode.param;

import java.io.Serializable;

/***
 * 楼宇信息
 * 
 * @author Administrator
 *
 */

public class BuildModel<T, G> extends BaseModel<T, G> implements Serializable {

	private String id;
	private String name;// 楼宇名称
	private String address;// 楼宇地址
	private String contact;// 联系电话
	private String manager;// 楼宇经理
	private String comment;// 物业概况
	private String propertyfee;// 物业费(平米每天)
	private String createuser;// 创建人
	private String createtime;// 创建时间
	private String updateuser;// 修改人
	private String updatetime;// 修改时间
	private String deleteuser;// 删除人
	private String deletetime;// 删除时间
	private String deleteflg;// 删除标记

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPropertyfee() {
		return propertyfee;
	}

	public void setPropertyfee(String propertyfee) {
		this.propertyfee = propertyfee;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
