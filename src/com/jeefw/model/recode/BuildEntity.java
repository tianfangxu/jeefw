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
 * 楼宇信息
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_build")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BuildEntity extends BaseEntity{
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "code", length = 32, nullable = false, unique = true)
	private String code       ;//楼宇编号
	@Column(name = "name")
	private String name       ;//楼宇名称
	@Column(name = "address")
	private String address    ;//楼宇地址
	@Column(name = "contact")
	private String contact    ;//联系电话
	@Column(name = "manager")
	private String manager    ;//楼宇经理
	@Column(name = "comment")
	private String comment    ;//物业概况
	@Column(name = "propertyfee")
	private String propertyfee;//物业费(平米每天)
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

	

}
