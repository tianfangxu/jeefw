package com.jeefw.model.recode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/***
 * 甲方合同主体信息
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_firstparty_contract")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
public class firstpartyContractEntity extends BaseEntity{

	@Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "id", length = 32, nullable = false,unique = true)
	private String id;// 主键
	@Column(name = "namea")
	private String namea;// 甲方姓名
	@Column(name = "address")
	private String address;// 地址
	@Column(name = "linkname")
	private String linkname;// 联系人
	@Column(name = "linkphone")
	private String linkphone;// 联系人电话
	@Column(name = "dutyparagraph")
	private String dutyparagraph;// 税号
	@Column(name = "bank")
	private String bank;// 开户银行
	@Column(name = "bankname")
	private String bankname;// 户名
	@Column(name = "banknumber")
	private String banknumber;// 开户银行账号
	@Column(name = "userid")
	private String userid;// 联系人id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNamea() {
		return namea;
	}
	public void setNamea(String namea) {
		this.namea = namea;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	
	public String getLinkphone() {
		return linkphone;
	}
	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}
	public String getDutyparagraph() {
		return dutyparagraph;
	}
	public void setDutyparagraph(String dutyparagraph) {
		this.dutyparagraph = dutyparagraph;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBanknumber() {
		return banknumber;
	}
	public void setBanknumber(String banknumber) {
		this.banknumber = banknumber;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
