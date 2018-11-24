package com.jeefw.model.recode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/***
 * 客户管理
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_customer")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CustomerEntity extends BaseEntity {

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "code", length = 32, nullable = false, unique = true)
	private String code;// 编号
	@Column(name = "idtype")
	private String idtype;// 证件种类
	@Column(name = "idnumber")
	private String idnumber;// 证件号码
	@Column(name = "idexpiresend")
	private String idexpiresend;// 证件有效期
	@Column(name = "name")
	private String name;// 名称
	@Column(name = "address")
	private String address;// 地址
	@Column(name = "contactname")
	private String contactname;// 联系人
	@Column(name = "contactnumber")
	private String contactnumber;// 联系电话
	@Column(name = "taxnumber")
	private String taxnumber;// 税号
	@Column(name = "account")
	private String account;// 银行账号
	@Column(name = "accountname")
	private String accountname;// 户名
	@Column(name = "bankname")
	private String bankname;// 开户行
	
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

	
}
