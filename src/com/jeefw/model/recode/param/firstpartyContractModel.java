package com.jeefw.model.recode.param;

import java.io.Serializable;

import core.support.ExtJSBaseParameter;

/***
 * 甲方合同主体信息
 * 
 * @author Administrator
 *
 */
public class firstpartyContractModel extends ExtJSBaseParameter implements Serializable{

	private String id;// 主键
	private String namea;// 甲方姓名
	private String address;// 地址
	private String linkname;// 联系人
	private String linkphone;// 联系人电话
	private String dutyparagraph;// 税号
	private String bank;// 开户银行
	private String bankname;// 户名
	private String banknumber;// 开户银行账号
	private String userid;// 联系人id
	private String createtime;		//创建时间			
	private String vflag;			//删除标志		0:未删除；1已删除
	
	private String page;
	
	private String rows;
	
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getVflag() {
		return vflag;
	}
	public void setVflag(String vflag) {
		this.vflag = vflag;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	

}
