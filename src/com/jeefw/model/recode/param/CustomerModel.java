package com.jeefw.model.recode.param;

import java.io.Serializable;

public class CustomerModel<T, G> extends BaseModel<T, G> implements
		Serializable {

	private String id;
	private String code;// 编号
	private String type;// 客户种类：0：企业；1：个人
	private String idtype;// 证件种类
	private String idnumber;// 证件号码
	private String idexpiresend;// 证件有效期
	private String name;// 名称
	private String address;// 地址
	private String contactname;// 联系人
	private String contactnumber;// 联系电话
	private String taxnumber;// 税号
	private String account;// 银行账号
	private String accountname;// 户名
	private String bankname;// 开户行
	private String createuser;// 创建人
	private String createtime;// 创建时间
	private String updateuser;// 修改人
	private String updatetime;// 修改时间
	private String deleteuser;// 删除人
	private String deletetime;// 删除时间
	private String deleteflg;// 删除标记

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getIdexpiresend() {
		return idexpiresend;
	}

	public void setIdexpiresend(String idexpiresend) {
		this.idexpiresend = idexpiresend;
	}

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

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
