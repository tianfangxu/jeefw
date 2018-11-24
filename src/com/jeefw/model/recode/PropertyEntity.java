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
 *楼宇内物业信息
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_property")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PropertyEntity extends BaseEntity{

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "code", length = 32, nullable = false, unique = true)
	private String code ;//编号
	@Column(name = "build")
	private String build;//楼宇
	@Column(name = "name")
	private String name ;//管理单元
	@Column(name = "area")
	private String area ;//面积
	@Column(name = "rent")
	private String rent ;//租金
	@Column(name = "used")
	private String used ;//租赁状态
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	
	
}
