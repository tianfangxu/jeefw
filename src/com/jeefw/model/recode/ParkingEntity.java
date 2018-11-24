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
 * 楼宇停车位信息
 * @author Administrator
 *
 */
@Entity
@Table(name = "m_parking")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ParkingEntity extends BaseEntity{

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "code", length = 32, nullable = false, unique = true)
	private String code ;//编号
	@Column(name = "build")
	private String build;//楼宇
	@Column(name = "name")
	private String name ;//名称
	@Column(name = "type")
	private String type ;//类型
	@Column(name = "area")
	private String area ;//面积
	@Column(name = "price")
	private String price;//价格
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}

	
	

}
