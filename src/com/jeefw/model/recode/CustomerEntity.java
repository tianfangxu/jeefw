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
public class CustomerEntity extends BaseEntity{
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "id", length = 32, nullable = false, unique = true)
	private String id;// 主键
	@Column(name = "cnumber")
	private String cnumber;// 客户编号
	@Column(name = "cname")
	private String cname;// 客户名称
	@Column(name = "ctype")
	private String ctype;// 客户类别（0：个人；1：企业）
	@Column(name = "gender")
	private String gender;// 性别（0）
	@Column(name = "cphone")
	private String cphone;// 联系电话（0,1）
	@Column(name = "postaddress")
	private String postaddress;// 通讯地址（0,1）
	@Column(name = "postalcode")
	private String postalcode;// 邮政编码（0,1）
	@Column(name = "idcard")
	private String idcard;// 身份证（0）
	@Column(name = "email")
	private String email;// 电子邮箱（0,1）
	@Column(name = "linkname")
	private String linkname;// 联系人（1）
	@Column(name = "cbank")
	private String cbank;// 开户银行（1）
	@Column(name = "cbankname")
	private String cbankname;// 户名（1）
	@Column(name = "cbanknumber")
	private String cbanknumber;// 开户银行账号（1）
	@Column(name = "cpargarph")
	private String cpargarph;// 税号（1）
	@Column(name = "corporation")
	private String corporation;// 法人（1）
	@Column(name = "contractnumber")
	private String contractnumber;// 合同编号
	@Column(name = "contractid")
	private String contractid;// 合同id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getPostaddress() {
		return postaddress;
	}
	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getCbank() {
		return cbank;
	}
	public void setCbank(String cbank) {
		this.cbank = cbank;
	}
	public String getCbankname() {
		return cbankname;
	}
	public void setCbankname(String cbankname) {
		this.cbankname = cbankname;
	}
	public String getCbanknumber() {
		return cbanknumber;
	}
	public void setCbanknumber(String cbanknumber) {
		this.cbanknumber = cbanknumber;
	}
	public String getCpargarph() {
		return cpargarph;
	}
	public void setCpargarph(String cpargarph) {
		this.cpargarph = cpargarph;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getContractnumber() {
		return contractnumber;
	}
	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}
	public String getContractid() {
		return contractid;
	}
	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	
	

}
